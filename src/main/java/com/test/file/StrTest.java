package com.test.file;

import java.util.HashMap;
import java.util.Map;

public class StrTest {
    public static void main(String[] args) {
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><CDO><CDOF N=\"cdoReturn\"><CDO><NF N=\"nCode\" V=\"0\"/><STRF N=\"strText\" V=\"OK\"/><STRF N=\"strInfo\" V=\"OK\"/></CDO></CDOF><CDOF N=\"cdoResponse\"><CDO><LF N=\"lAmount\" V=\"450846\"/><LF N=\"lBorrowerId\" V=\"4000044\"/><NF N=\"nState\" V=\"0\"/><LF N=\"lTaskId\" V=\"40000009620\"/></CDO></CDOF></CDO>";
        String[] list =  xml.split("><");
        for (String s:list){
           // System.out.println(s);
            if(s.contains("strText\" V=\"OK\"")){
             //   System.out.println(s);
                String ss = s.substring(8,s.length()-1);
                System.out.println(ss);
//               String [] ss = s.split(" ");
//                for (String s1:ss){
//                    //System.out.println(s1);
//                    if(s1.contains("V=")){
//                        String s2 = s1.substring(0,s1.length()-1);
//                        System.out.println(s2);
//                    }
//                }
            }
        }
    }
}
