package com.test.file;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Student {
	private String name;
	private String width;
	private String height;
	private int age;
	//private Set<Book> list;
	public Student(String name, String width, String height, int age) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Student() {
		super();
	}
//	// @XmlElement(name="sub")
//	public Set<Book> getList() {
//		return list;
//	}
//	public void setList(Set<Book> list) {
//		this.list = list;
//	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", width='" + width + '\'' +
				", height='" + height + '\'' +
				", age=" + age +
			//	", list=" + list +
				'}';
	}
}

