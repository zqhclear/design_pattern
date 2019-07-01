package exclutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author puzl
 * @Date 2017/10/23
 * @Description: excel解析工厂，支持xsl、xlsx、csv
 */
public class ReaderFactory {

    /**
     * @param path 文件绝对路径
     * @return
     * @throws Exception
     */
    public static AbstractExcelReader create(String path) throws Exception {
       return create(path, CSVFileReader.SEPARATOR_COMMA);
    }


    /**
     * @param path 文件绝对路径
     * @param separator csv文件的分隔符
     * @return
     * @throws Exception
     */
    public static AbstractExcelReader create(String path, char separator) throws Exception {

        if (!ExcelUtils.validateFileExit(path)) {
            throw new FileNotFoundException();
        }
        if (ExcelUtils.isExcel2003(path)) {
            return new XLSFileReader(new FileInputStream(path));
        } else if (ExcelUtils.isExcel2007(path)) {

            return new XLSXFileReader(new FileInputStream(path));
        } else if (ExcelUtils.isCsv(path)) {
            return new CSVFileReader(new FileInputStream(path), separator);
        } else {
            throw new RuntimeException("不支持的文件类型");
        }
    }

    /**
     * @param inputStream 文件流对象
     * @param fileType 枚举
     * @return
     * @throws Exception
     */
    public static AbstractExcelReader create(InputStream inputStream, FileType fileType) throws Exception {
        return create(inputStream, fileType, CSVFileReader.SEPARATOR_COMMA);
    }

    /**
     * @param inputStream 文件流对象
     * @param fileType 枚举
     * @return
     * @throws Exception
     */
    public static AbstractExcelReader create(InputStream inputStream, FileType fileType, char separator) throws Exception {

        switch (fileType){
            case xls:
                return new XLSFileReader(inputStream);
            case xlsx:
                return new XLSXFileReader(inputStream);
            case csv:
                return new CSVFileReader(inputStream, separator);
            default:
                throw new RuntimeException("不支持的文件类型");
        }
    }

    public static enum FileType{
        xls, xlsx, csv;
    }

}
