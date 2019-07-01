package exclutil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author puzl
 * @Date 2017/10/23
 * @Description: excel 读取抽象类
 */
public abstract class AbstractExcelReader {

    public AbstractExcelReader(InputStream is) {
        this.inputStream = is;
    }

    /**
     * 起始行数,可以配置，比如调过表头
     */
    protected int startRows;

    /**
     * 停止标记
     */
    protected boolean isStop = false;

    /**
     * 文件路径
     */
    protected InputStream inputStream;

    /**
     * 数据处理器
     */
    protected ReadDataHandler readDataHandler;

    /**
     * 游标
     */
    protected int index = 0;

    /**
     * 一共解析的数据量
     */
    protected int dataIndex = 0;

    public String getDateFormatStr() {
        return dateFormatStr;
    }

    public void setDateFormatStr(String dateFormatStr) {
        this.dateFormatStr = dateFormatStr;
    }

    /**
     * 时间格式,默认 yyyy-mm-dd HH:mm:ss
     */
    private String dateFormatStr = "yyyy-mm-dd HH:mm:ss";
    /**
     * 执行解析操作
     *
     * @return 读取的Excel行数
     */
    public abstract int parse(ReadDataHandler readDataHandler) throws Exception;

    /**
     * 停止解析
     * @throws Exception
     */
    public void stop(){
        this.isStop = true;
    }

    public int getStartRows() {
        return startRows;
    }

    public void setStartRows(int startRows) {
        this.startRows = startRows;
    }


    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    protected String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
                        cellvalue = sdf.format(date);
                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

}
