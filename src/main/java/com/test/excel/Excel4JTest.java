package com.test.excel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.crab2died.ExcelUtils;

public class Excel4JTest {
	
	public static void main(String[] args) {
		String path =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"apitest.xlsx";
		String path2 =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"apitest2.xlsx";
		String filepath =System.getProperty("user.dir")+File.separator+"testdata"+File.separator;
		try {
			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path,TestCase.class);
			for (TestCase testCase : list) {
				System.out.println(testCase);
				testCase.setResult("ok");
			}
			
			 ExcelUtils.getInstance().exportObjects2Excel(list, TestCase.class, filepath+"TestCase.xlsx");


			  Map<String, Object> data = new HashMap<String, Object>();
		        data.put("testcount", "10");
		        data.put("rate", "100%");
			//  ExcelUtils.getInstance().exportObjects2Excel(path2, 0, list, data, TestCase.class, false, "D:/test1.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
