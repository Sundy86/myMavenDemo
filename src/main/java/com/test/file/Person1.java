package com.test.file;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person1 {
	private String name;
	private String sex;
	private int age;
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	
	private Set<Book> books;
	
	public Person1() {
		
	}

	@XmlElements(value={@XmlElement(name="books", type=Book.class)})
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	
	@XmlElement(name="hello2")
	public String getP4() {
		return p4;
	}
	public void setP4(String p4) {
		this.p4 = p4;
	}
	public Person1(String name, String sex, int age, String p1, String p2, String p3, String p4) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
	}
	@Override
	public String toString() {
		return "Person1 [name=" + name + ", sex=" + sex + ", age=" + age + ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3
				+ ", p4=" + p4 + "]";
	}
	

}
