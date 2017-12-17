package com.test.json;

import java.util.Date;

/**
 * Created by Suncy on 2017/12/17 0017.
 */
public class Student {
    private String name;
    private int age;
    private Date date;

    public Student() {
        // TODO Auto-generated constructor stub
    }

    public Student(String name,int age,Date date){
        this.name = name;
        this.age = age;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
