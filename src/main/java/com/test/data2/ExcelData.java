package com.test.data2;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

}
