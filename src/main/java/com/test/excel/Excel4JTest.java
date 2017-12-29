package com.test.excel;

import java.io.File;
import java.util.*;

import com.github.crab2died.ExcelUtils;

public class Excel4JTest {
	public static void test() throws Exception {

		Map<String, List<?>> classes = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		data.put("title", "战争学院花名册");
		data.put("info", "学校统一花名册");
//		for(int i=1;i<=10;i++){
//				classes.put("class_" + i, Collections.singletonList(
//						new Student1("101000" + i, "李四" + i, "一年级" + i + "班")
//				));
//		}

		classes.put("class_one", Arrays.asList(
				new Student1("1010009", "李四", "一年级一班"),
				new Student1("1010002", "古尔丹", "一年级三班")
		));
		classes.put("class_two", Collections.singletonList(
				new Student1("1010008", "战三", "二年级一班")
		));
		classes.put("class_three", Arrays.asList(
				new Student1("1010004", "萝卜特", "三年级二班"),
				new Student1("1010005", "奥拉基", "三年级二班")
		));
		classes.put("class_four", Collections.singletonList(
				new Student1("1010006", "得嘞", "四年级二班")
		));
		classes.put("class_six", Arrays.asList(
				new Student1("1010001", "盖伦", "六年级三班"),
				new Student1("1010003", "蒙多", "六年级一班")
		));
		String path1 =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"map_template.xlsx";
		try {
			ExcelUtils.getInstance().exportObject2Excel(path1,
                    0, classes, data, Student1.class, false, "C.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		 test();
//		String path =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"apitest.xlsx";
//		String path2 =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"apitest2.xlsx";
//		String filepath =System.getProperty("user.dir")+File.separator+"testdata"+File.separator;
//		try {
//			List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path,TestCase.class);
//			for (TestCase testCase : list) {
//				System.out.println(testCase);
//				testCase.setResult("ok");
//				testCase.setDateTime(new Date());
//			}
//
//			   ExcelUtils.getInstance().exportObjects2Excel(list, TestCase.class, filepath+"TestCase.xlsx");
//
//
//			    Map<String, Object> data = new HashMap<String, Object>();
//		        data.put("num", "10");
//		        data.put("rate", "100%");



	}

}
