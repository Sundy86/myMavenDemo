package com.test.file;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Suncy on 2017/12/12 0012.
 */
public class XmlUtils2 {
    public static void main(String args[]){
        JAXBContext jc;
        try {
            jc=JAXBContext.newInstance(Person2.class);
            Marshaller ms = jc.createMarshaller();
            Person2 person = new Person2("zhangsan", "11", 20, "test1", "test2", "test3", "test4");
            Book book1 = new Book("book1",120);
            Book book2 = new Book("book2",150);
            Book book3 = new Book("book3",150);
            Set<Book> books = new HashSet<Book>();
            books.add(book1);
            books.add(book2);
            books.add(book3);
            person.setBooks(books);
            String path=System.getProperty("user.dir")+File.separator+"data"+File.separator;
            File file = new File(path+"person2.xml");
            //将Person2对象写入xml中
            ms.marshal(person, file);
            //将Person2对象输出
            ms.marshal(person,System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
