package com.test.file;

import java.io.File;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtils {
	
	public static void main(String[] args) {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Person1.class);
			Marshaller ms = jc.createMarshaller();
			Person1 person1 = new Person1("zhangsan", "11", 20, "test1", "test2", "test3", "test4");
			Book book =new Book("book1", 110);
			Book boo2 =new Book("book2", 100);
			Set<Book> booklist = new HashSet<Book>();
 			booklist.add(book);
 			booklist.add(boo2);
			person1.setBooks(booklist);

			String path=System.getProperty("user.dir")+File.separator+"testdata"+File.separator;
			File file = new File(path+"test.xml");
			//将person对象转化成xml后的内容输出到控制台
			ms.marshal(person1, System.out);
			//将person对象转化成xml后的内容输入到test.xml文件中
			ms.marshal(person1, file);
			
			
	        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>11</age><height>h</height><name>zhang</name><width>w</width></student>";  
	        JAXBContext jc2 = JAXBContext.newInstance(Student.class);  
	        Unmarshaller unmar = jc2.createUnmarshaller();
	        //将xml字符串中的内容转化成 student对象，并输出
	        Student stu = (Student) unmar.unmarshal(new StringReader(xml));
	        System.out.println(stu);

	        JAXBContext jc3 = JAXBContext.newInstance(Person1.class);
	        Unmarshaller unmar3 = jc3.createUnmarshaller();
	        String path3=System.getProperty("user.dir")+File.separator+"testdata"+File.separator;
	        File file3 = new File(path3+"test.xml");
	        //将xml转化成person对象 并输出
	        Person1 person = (Person1) unmar3.unmarshal(file3);
	        System.out.println(person);
		} catch (JAXBException e) {
			e.printStackTrace();
		}  
	}

}
