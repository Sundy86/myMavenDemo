package com.test.example.test02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableTest {
    public static void main(String[] args) {
        List<User> list = new ArrayList<User> ();
        User user1 = new User("aa",12);
        User user2 = new User("bb",23);
        User user3 = new User("cc",10);
        User user4 = new User("dd",11);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        Collections.sort(list);
        for(User o:list){
            System.out.println("name = "+o.getName()+"  age = "+o.getAge());
        }

    }
}
