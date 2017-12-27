package com.test.excel;

import java.util.Map;

import com.github.crab2died.annotation.ExcelField;


public class TestCase {
	
	@ExcelField(title = "类型")
	private String type;
	
	@ExcelField(title = "地址")
	private String url;
	
	@ExcelField(title = "参数")
	private String params;
	
	 @ExcelField(title = "头部",order=2)
	private String header;
	
	@ExcelField(title = "测试结果")
	//@ExcelField(title = "测试结果", order=1, writeConverter = ResultWirteConverible.class)
	private String result;
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "TestCase [type=" + type + ", url=" + url + ", params=" + params + ", header=" + header + ", result="
				+ result + "]";
	}

}
