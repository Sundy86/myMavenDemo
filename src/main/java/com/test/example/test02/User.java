package com.test.example.test02;

public class User implements Comparable<User>{
     public int age;
     public String name;
    public User(String name,int age) {
        this.name=name;
        this.age=age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        if(o != null ){
            if(this.getAge() > o.getAge()){
                return 1;  //升序排列
            }else{
                return -1;//降序排列
            }
        }
        return 0;
    }
}
