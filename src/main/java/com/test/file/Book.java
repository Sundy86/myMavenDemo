package com.test.file;

public class Book {
	private String name;
	private long price;
	
	public Book() {
		
	}
	
	public Book(String name, long price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	

}
