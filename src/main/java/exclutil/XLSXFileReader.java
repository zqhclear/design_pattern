package exclutil;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;


/**
 * @Author puzl
 * @Date 2017/10/23
 * @Description: xlsx读取类，适用于大数据量
 */
public class XLSXFileReader extends AbstractExcelReader {

    private static Logger logger = LoggerFactory.getLogger(XLSXFileReader.class);

    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    private Iterator<InputStream> sheets;
    private XMLReader parser;

    private int sheetIndex;

    /**
     * 读大数据量Excel
     *
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    public XLSXFileReader(InputStream inputStream) throws Exception {
        super(inputStream);
        init();
    }

    /**
     * 初始化 将Excel转换为XML
     *
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    private void init() throws IOException, OpenXML4JException, SAXException {
        OPCPackage pkg = OPCPackage.open(inputStream);
        XSSFReader xssfReader = new XSSFReader(pkg);
        SharedStringsTable sharedStringsTable = xssfReader.getSharedStringsTable();
        StylesTable stylesTable = xssfReader.getStylesTable();
        sheets = xssfReader.getSheetsData();
        parser = fetchSheetParser(sharedStringsTable, stylesTable);
    }

    /**
     * 执行解析操作
     *
     * @return 读取的Excel行数
     */
    @Override
    public int parse(ReadDataHandler readDataHandler) throws Exception {
        this.readDataHandler = readDataHandler;
        while (sheets.hasNext()) {
            index = 0;
            InputStream sheet = sheets.next();
            try {
                parser.parse(new InputSource(sheet));

                sheetIndex++;
            } catch (Exception e) {
                logger.error("读取解析异常：", e);
                break;
            } finally {
                if (sheet != null) {
                    try {
                        sheet.close();
                    } catch (IOException e) {
                        logger.error("sheet流关闭异常：", e);
                    }
                }
            }
        }

        return dataIndex;
    }

    private XMLReader fetchSheetParser(SharedStringsTable sharedStringsTable, StylesTable stylesTable) throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        ContentHandler handler = new SheetHandler(sharedStringsTable, stylesTable);
        parser.setContentHandler(handler);
        parser.setErrorHandler(new ErrorProcessor());
        return parser;
    }

    /**
     * SAX解析的处理类
     */
    private class SheetHandler extends DefaultHandler {
        private SharedStringsTable sharedStringsTable; // 存放映射字符串
        private StylesTable stylesTable;// 存放单元格样式
        private String readValue;// 存放读取值
        private xssfDataType dataType;// 单元格类型
        private String[] rowDatas;// 存放一行中的所有数据
        private int colIdx;// 当前所在列
        private String formatString;
        private DataFormatter formatter;


        private short formatIndex;

        private SheetHandler(SharedStringsTable sst, StylesTable stylesTable) {
            this.sharedStringsTable = sst;
            this.stylesTable = stylesTable;
            this.formatter = new DataFormatter();

        }

        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {
            if (name.equals("c")) {// c > 单元格
                colIdx = getColumn(attributes);
                String cellType = attributes.getValue("t");
                String cellStyle = attributes.getValue("s");

                this.dataType = xssfDataType.NUMBER;
                if ("b".equals(cellType)) {
                    this.dataType = xssfDataType.BOOL;
                } else if ("e".equals(cellType)) {
                    this.dataType = xssfDataType.ERROR;
                } else if ("inlineStr".equals(cellType)) {
                    this.dataType = xssfDataType.INLINESTR;
                } else if ("s".equals(cellType)) {
                    this.dataType = xssfDataType.SSTINDEX;
                } else if ("str".equals(cellType)) {
                    this.dataType = xssfDataType.FORMULA;
                } else if (cellStyle != null) {
                    int styleIndex = Integer.parseInt(cellStyle);
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex = style.getDataFormat();
                    this.formatString = style.getDataFormatString();
                    if (this.formatString == null)
                        this.formatString = BuiltinFormats
                                .getBuiltinFormat(this.formatIndex);
                }
            }
            // 解析到一行的开始处时，初始化数组
            else if (name.equals("row")) {
                int cols = getColsNum(attributes);// 获取该行的单元格数
                rowDatas = new String[cols];
            }
            readValue = "";
        }

        public void endElement(String uri, String localName, String name)
                throws SAXException {
            if (name.equals("v")) { // 单元格的值

                switch (this.dataType) {
                    case BOOL: {
                        char first = readValue.charAt(0);
                        rowDatas[colIdx] = first == '0' ? "FALSE" : "TRUE";
                        break;
                    }
                    case ERROR: {
                        rowDatas[colIdx] = "ERROR:" + readValue.toString();
                        break;
                    }
                    case INLINESTR: {
                        rowDatas[colIdx] = new XSSFRichTextString(readValue).toString();
                        break;
                    }
                    case SSTINDEX: {
                        int idx = Integer.parseInt(readValue);
                        rowDatas[colIdx] = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx)).toString();
                        break;
                    }
                    case FORMULA: {
                        rowDatas[colIdx] = readValue;
                        break;
                    }
                    case NUMBER: {
                        // 判断是否是日期格式
                        if (HSSFDateUtil.isADateFormat(formatIndex, readValue)) {
                            Double d = Double.parseDouble(readValue);
                            Date date = HSSFDateUtil.getJavaDate(d);
                            rowDatas[colIdx] = DateFormatUtils.format(date, getDateFormatStr());
                        } else if (this.formatString != null) {

                            rowDatas[colIdx] = formatter.formatRawCellContents(
                                    Double.parseDouble(readValue), this.formatIndex,
                                    this.formatString);
                        } else {
                            rowDatas[colIdx] = readValue;
                        }
                        break;
                    }
                }
            }
            // 当解析的一行的末尾时，输出数组中的数据
            else if (name.equals("row")) {
                if (index >= startRows) {
                    readDataHandler.readLine(rowDatas, index, sheetIndex);
                    if(isStop){
                        throw new SAXException("parse has been interrupted");
                    }

                    dataIndex++;
                }
                index++;
            }
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            readValue += new String(ch, start, length);
        }
    }

    private int getColumn(Attributes attrubuts) {
        String name = attrubuts.getValue("r");
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            if (Character.isDigit(name.charAt(i))) {
                break;
            }
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    private int getColsNum(Attributes attrubuts) {
        String spans = attrubuts.getValue("spans");
        String cols = spans.substring(spans.indexOf(":") + 1);
        return Integer.parseInt(cols);
    }

}
