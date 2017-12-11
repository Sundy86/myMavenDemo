package com.test.ref;

import java.lang.reflect.Field;

public class CopyBeansTest2 {
    public static void main(String args[]){
        Person1 p1 = new Person1("张三", "男",28,"test1","test2","test3","test4");
        Person2 p2 =new Person2();
        getBean(Person1.class,p1);
        getBean(Person2.class,p2);
        copyBean(p1,p2);
        System.out.println(p2);

    }

    public static void getBean(Class clz,Object obj){
        Field[] fields = clz.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            try {
                System.out.println(field.getName()+"----"+field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public  static void copyBean(Object from,Object to){
      Class person1 =  from.getClass();
      Class person2 = to.getClass();
        //获取person2的所有属性
      Field[] person2Fields = person2.getDeclaredFields();
      for (Field p2field:person2Fields){
          p2field.setAccessible(true); //设置person2可以访问
          System.out.println(p2field.getName());
          try {
              //获取person1的对应属性
              Field p1field = person1.getDeclaredField(p2field.getName());
              //设置person1可以访问
              p1field.setAccessible(true);
              //将person1的对应属性值 给person2
              p2field.set(to,p1field.get(from));
          } catch (Exception e) {
              e.printStackTrace();
          }


      }

    }
}
