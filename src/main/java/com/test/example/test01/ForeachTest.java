package com.test.example.test01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForeachTest {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5};
        //��һ�ַ�ʽ����
        System.out.println("һά���飬��һ�ַ�ʽ����:");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }
        //�ڶ��ַ�ʽ����
        System.out.println("һά���飬�ڶ��ַ�ʽ����:");
        for(int a:arr){
            System.out.println(a);
        }

        int[][]arr2 = {{1,3,5},{2,4,6},{8,9,10},{11,12,13}};
        System.out.println("��ά���飬�ڶ��ַ�ʽ����:");
        for(int [] row:arr2){
            for(int val:row){
                System.out.println(val+"  ");
            }
        }

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println("ArrayList����һ�ַ�ʽ����:");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i)+" ");
        }
        System.out.println("ArrayList���ڶ��ַ�ʽ����:");
        Iterator<String> it =list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());

        }

        System.out.println("ArrayList�������ַ�ʽ����:");
        for(String s : list){
            System.out.println(s);
        }
    }
}
