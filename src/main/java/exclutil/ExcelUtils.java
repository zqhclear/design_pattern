package exclutil;
import java.io.File;

/**
 * @Author puzl
 * @Date 2017/10/24
 * @Description:
 */
public class ExcelUtils {

    /**
     * 是否是2003的excel(xls)，返回true是2003
     * @param filePath  文件完整路径
     * @return boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel，返回true是2007
     *
     * @param filePath  文件完整路径
     * @return boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    /**
     * 是否是csv格式的文件，返回true是csv格式
     * @param filePath  文件完整路径
     * @return boolean
     */
    public static boolean isCsv(String filePath) {
        return filePath.matches("^.+\\.(?i)(csv)$");
    }

    public static boolean validateFileExit(String filePath) {
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public static String toUpperFristChar(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

}
