package exclutil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author puzl
 * @Date 2017/10/24
 * @Description: excel文件写入，xlsx格式
 */
public class XLSXFileWriter<T> extends AbstractExcelWriter<T> {

    private int index;
    private Sheet writeSheet;

    private Workbook wb;


    public XLSXFileWriter(String filePath)throws  Exception {
        super(filePath);
        wb = new SXSSFWorkbook(1000);
    }

    private void createSheet(List<String> header) {
        writeSheet = wb.createSheet(String.format("sheet%s",index / sheetSize));
        // 写入表头
        if(header != null){
            Row writeRow = writeSheet.createRow(index % (sheetSize+1));
            for(int j = 0; j < header.size(); j++){
                Cell cell = writeRow.createCell(j);
                cell.setCellValue(header.get(j));
            }
            index++;
        }
    }


    @Override
    public void writeByBean(List<T> list,List<String> header) throws Exception{
        int tmpSheetSize = sheetSize;
        if(header != null){
            tmpSheetSize = tmpSheetSize + 1;
        }
        for(T t : list){
            if(index % tmpSheetSize == 0){
                createSheet(header);
            }
            Row writeRow = writeSheet.createRow(index % tmpSheetSize);
            Field[] fields = t.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                Cell cell = writeRow.createCell(j);
                Method m = t.getClass().getDeclaredMethod("get"+ ExcelUtils.toUpperFristChar(field.getName()));
                Object value = m.invoke(t);
                if("java.util.Date".equals(field.getGenericType().getTypeName())){
                    cell.setCellValue(dateFormat.format(value));
                }else{
                    cell.setCellValue(value.toString());
                }
            }
            index++;
        }
    }

    @Override
    public void writeByArray(List<Object[]> list,List<String> header) throws Exception{
        int tmpSheetSize = sheetSize;
        if(header != null){
            tmpSheetSize = tmpSheetSize + 1;
        }
        for(Object[] objects : list){
            if(index % tmpSheetSize == 0){
                createSheet(header);
            }
            Row writeRow = writeSheet.createRow(index % tmpSheetSize);
            for (int j = 0; j < objects.length; j++) {
                Cell cell = writeRow.createCell(j);
                if("java.util.Date".equals(objects[j].getClass().getTypeName())){
                    cell.setCellValue(dateFormat.format(objects[j]));
                }else{
                    cell.setCellValue(objects[j].toString());
                }
            }
            index++;
        }
    }

    @Override
    public void output(OutputStream outputStream) throws Exception {
        wb.write(outputStream);
        wb.close();
    }
}
