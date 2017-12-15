package com.test.data2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.test.PropertiesUtil.DATA_PATH;

public class ExcelData {

    public Object[][] getData(String moduleName, String testcaseName){
        return getData(moduleName, testcaseName, 0);
    }

    public Object[][] getData(String moduleName, String testcaseName, int rowNum){
        Object[][] data = null;

        try {
            data = addList(moduleName, testcaseName, rowNum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;

    }

    private Object[][] addList(String moduleName, String testcaseName, int rowNum) throws FileNotFoundException{
        ArrayList<Object> list = new ArrayList<Object>();
        //拼接excel文件路径
        InputStream is = new FileInputStream(DATA_PATH + testcaseName);
        Object[][] data = null;

        try{
            XSSFWorkbook  excelWbook = new XSSFWorkbook(is);

            XSSFSheet excelWSheet = excelWbook.getSheet(moduleName);
            int rowTotalNum = excelWSheet.getLastRowNum();
            //System.out.println(rowTotalNum);
            int colTotalNum = excelWSheet.getRow(0).getPhysicalNumberOfCells();
            //System.out.println(colTotalNum);

            if(excelWSheet != null){
                for(int i = 1; i<=rowTotalNum;i++){
                    for(int j=0;j<colTotalNum;j++){

                        XSSFRow row = excelWSheet.getRow(i);
                        XSSFCell cell = row.getCell(j);
                        DataFormatter formatter = new DataFormatter();
                        String text = formatter.formatCellValue(cell);
                        list.add(text);
                    }
                }
                //System.out.println(list);
            }


            if(rowNum == 0){
                data = new Object[rowTotalNum][colTotalNum];
                //System.out.println(data);
                int k = -1;
                for(int i = 0;i<rowTotalNum;i++){
                    for(int j = 0;j<colTotalNum; j++){
                        if(k<list.size())
                            k++;
                        data[i][j] = list.get(k);
                        //System.out.println("i="+i+","+"j="+j+","+data[i][j]);
                    }
                }
            }else{
                int k = -1;
                data = new Object[rowNum][colTotalNum];
                for(int i = 0;i<rowNum;i++){
                    for(int j = 0; j<colTotalNum;j++){
                        if(k<list.size())
                            k++;
                        if(i<=(rowNum-1)){
                            data[i][j] = list.get(k);
                        }
                    }
                }
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    //从Excel文件获取测试数据的静态方法
    public static Object[][] getData(String filePath,String FileName,String sheetName) throws IOException{
        //  LogUtil.logInfo(filePath);
        //根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
        File file = new File(filePath + FileName);
        //创建FileInputStream对象用于读取Excel文件
        FileInputStream inputStream = new FileInputStream(file);
        Workbook Workbook = null;
        //获取文件名参数的扩展名，判断是.xlsx文件还是.xls文件
        String fileExtensionName = FileName.substring(FileName
                .indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            Workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            Workbook = new HSSFWorkbook(inputStream);
        }
        //通过sheetName参数，声称Sheet对象
        Sheet Sheet = Workbook.getSheet(sheetName);
        //获取Excel数据文件Sheet1中数据的行数，getLastRowNum()方法获取数据的最后一行行号
        //getFirstRowNum()方法获取数据的第一行行号，相减之后得出数据的行数，Excel文件的行号和列号都是从0开始
        int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
        //创建list对象存储从Excel数据文件读取的数据
        List<Object[]> records = new ArrayList<Object[]>();
        //循环遍历Excel数据文件的所有数据，除了第一行，第一行是数据列名称
        for (int i = 1; i < rowCount + 1; i++) {
            //使用getShow方法获取行对象
            Row row = Sheet.getRow(i);
            //声明一个数组，存储Excel数据文件每行中的3个数据，数组的大小用getLastCellNum()方法进行动态声明，实现测试数据个数和数组大小一致
            String fields[] = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                //使用getCell()和getStringCellValue()方法获取Excel文件中的单元格数据
                fields[j] =row.getCell(j).getStringCellValue();
            }
            //将fields的数据对象存入records的list中
            records.add(fields);
        }
        // 将存储测试数据的List转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二位数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }
}
