package exclutil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.Arrays;

/**
 * @Author puzl
 * @Date 2017/10/23
 * @Description: xls读取类，推荐小数据量情况下使用
 */
public class XLSFileReader extends AbstractExcelReader {

    public XLSFileReader(InputStream is){
        super(is);
    }

    @Override
    public int parse(ReadDataHandler readDataHandler)throws  Exception {
        Workbook workbook;
        workbook = new HSSFWorkbook(inputStream);
        // 开始解析
        label:for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            index = startRows;
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            String[] values = new String[sheet.getRow(0).getPhysicalNumberOfCells()];
            Arrays.fill(values, "");
            for (int rowIndex = startRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        values[columnIndex] = getCellFormatValue(cell);
                    }
                }
                readDataHandler.readLine(values, index++, sheetIndex);
                if(isStop){
                    break label;
                }
                dataIndex++;
            }
        }
        return dataIndex;
    }

}
