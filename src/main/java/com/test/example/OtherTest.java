package com.test.example;

import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

public class OtherTest {
    static String wholeString="SELECT f.claim_status FROM t_claim_wf f，t_claim tc where f.claim_id=tc.claim_id and tc.accident_no = '#_#' and loss_subject='#_#'";
    static String subString="#_#";
    @Test
    public static void countString()
    {
        int index=0;
        int count=0;
        int from=0;
        while((index=wholeString.indexOf(subString,from))!=-1)
        {
            from=index+subString.length();
            System.out.println(from+"--------------"+subString.length());
            count++;
        }
        System.out.print(count);

        System.out.println("------------"+Arrays.toString("堎".getBytes(Charset.forName("gbk"))));

        System.out.println(new String(new byte[]{-120, -39},Charset.forName("GBK")));
    }
}
