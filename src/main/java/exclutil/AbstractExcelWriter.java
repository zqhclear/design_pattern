package exclutil;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author puzl
 * @Date 2017/10/24
 * @Description:
 */
public abstract class AbstractExcelWriter<T> {


    /**
     * 时间格式,默认 yyyy-mm-dd HH:mm:ss
     */
    protected String dateFormatStr = "yyyy-mm-dd HH:mm:ss";


    protected SimpleDateFormat dateFormat;

    public String getDateFormatStr() {
        return dateFormatStr;
    }

    public void setDateFormatStr(String dateFormatStr) {
        this.dateFormatStr = dateFormatStr;
        dateFormat = new SimpleDateFormat(dateFormatStr);
    }

    /**
     * 每个sheet的数据行数上限，默认50000，不建议再大
     */
    protected int sheetSize = 50000;


    protected String filePath;
    public AbstractExcelWriter(String filePath){
        dateFormat = new SimpleDateFormat(dateFormatStr);
        this.filePath = filePath;
    }



    public int getSheetSize() {
        return sheetSize;
    }

    public void setSheetSize(int sheetSize) {
        this.sheetSize = sheetSize;
    }

    /**
     * @description 传入java对象即可导出，但是列顺序不可控
     * @param list 导出的对象list
     * @param header 第一行表头，为空时无表头
     * @throws Exception
     */
    public abstract void writeByBean(List<T> list, List<String> header) throws Exception;

    /**
     * @description 传入object数据，推荐使用，可控制顺序
     * @param list 导出的对象list
     * @param header 第一行表头，为空时无表头
     * @throws Exception
     */
    public abstract void writeByArray(List<Object[]> list, List<String> header) throws Exception;


    /**
     * @description 输出流
     * @param outputStream
     * @throws Exception
     */
    public abstract void output(OutputStream outputStream) throws Exception;

}
