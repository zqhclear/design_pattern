package exclutil;

/**
 * @Author puzl
 * @Date 2017/10/23
 * @Description:
 */
public class WriterFactory {
    public static AbstractExcelWriter create(String path)throws Exception{

//        if(ExcelUtils.isExcel2003(path)){
//            throw new RuntimeException("不支持的文件类型");
//        }else if(ExcelUtils.isExcel2007(path)){
            return new XLSXFileWriter(path);
//        }else if(ExcelUtils.isCsv(path)){
//            throw new RuntimeException("不支持的文件类型");
//        }else{
//            throw new RuntimeException("不支持的文件类型");
    }
}
