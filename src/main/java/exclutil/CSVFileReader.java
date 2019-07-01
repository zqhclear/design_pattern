package exclutil;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author puzl
 * @Date 2017/10/23
 * @Description: excel 读取类
 */
public class CSVFileReader extends AbstractExcelReader{

    private static Logger logger = LoggerFactory.getLogger(CSVFileReader.class);

    /**
     * 逗号分隔符
     */
    public static final char SEPARATOR_SPACE = ' ';

    /**
     * 空格分隔符
     */
    public static final char SEPARATOR_COMMA = ',';

    /**
     * tab分隔符
     */
    public static final char SEPARATOR_TAB = '\t';


    private char separator = SEPARATOR_SPACE;

    public CSVFileReader(InputStream inputStream, char separator) {
        super(inputStream);
        this.separator = separator;
    }

    @Override
    public int parse(ReadDataHandler readDataHandler) throws Exception {
        CSVReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            // 用5M的缓冲读取文本文件
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new BufferedInputStream(inputStream), "utf-8"), 5 * 1024 * 1024);
            reader = new CSVReader(bufferedReader, separator);
            String[] nextRow = null;
            while ((nextRow = reader.readNext()) != null) {
                if (nextRow == null || nextRow.length <= 0 || index < startRows) {
                    index++;
                    continue;
                }

                readDataHandler.readLine(nextRow, index, 0);

                if (isStop) {
                    break;
                }
                index++;

            }
        } catch (Exception e) {
            logger.error("读取异常", e);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                logger.error("关闭csv reader异常", e);
            }
            try {
                bufferedReader.close();
            } catch (Exception e) {
                logger.error("关闭csv bufferedReader异常", e);
            }

        }

        return index;

    }

}