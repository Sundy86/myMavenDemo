package com.test.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.*;

/**
 * Created by Suncy on 2017/12/30 0030.
 */
public class AviatorTest {
    public static void main(String[] args) {
        //Aviator的数值类型仅支持Long和Double, 任何整数都将转换成Long, 任何浮点数都将转换为Double, 包括用户传入的变量数值
        Long result = (Long) AviatorEvaluator.execute("22+1+100");
        System.out.println(result);//123

        String  myname = "Lily";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("myname",myname);
        //Aviator的String是任何用单引号或者双引号括起来的字符序列
        //方法一：
        String result1 = (String) AviatorEvaluator.execute(" 'hello '+ myname",map);
        System.out.println(result1);
        //方法二：
        Object result11 = AviatorEvaluator.exec("'hello '+ myname",myname);
        System.out.println(result11.toString());

        String result2 = (String) AviatorEvaluator.execute("\"a\'b\'c\"");//输出：a'b'c
        String result3 = (String) AviatorEvaluator.execute("'a\"b\"c'");//输出：a"b"c
        String result4 = (String) AviatorEvaluator.execute("'a\"b\"c' + 3");//输出：a"b"c3
        String result5 = (String) AviatorEvaluator.execute("'a\"b\"c' + hello");//输出：a"b"cnull
        System.out.println(result4+" "+result5);

        //通过string.substring('habcello', 1, 3)获取字符串'ab', 然后通过函数string.contains判断e是否在'abc'中
        System.out.println(AviatorEvaluator.execute("string.substring('habcello',1,3)"));//ab
        System.out.println(AviatorEvaluator.execute("string.contains(\"abc\",\"ab\")"));//true
        System.out.println(AviatorEvaluator.execute("string.contains(\"abc\",string.substring('habcello',1,3))")); //输出：true


        //通过compile方法可以将表达式编译成Expression的中间对象,
        // 当要执行表达式的时候传入map1并调用Expression的execute方法即可
        String expression = "a/(b+c)>10";
        Expression exp =  AviatorEvaluator.compile(expression);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("a",100);
        map1.put("b",0);
        map1.put("c",5);
        Boolean bool = (Boolean) exp.execute(map1);
        System.out.println(bool);//true

        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");

        int[] nums = new int[5];
        for(int i=0;i<nums.length;i++){
            nums[i]=i;
        }

        Map<String,Object> map2 = new HashMap<String,Object>();
       // map2.put("date",DateUtils.date2Str(new Date(),DateUtils.DATE_FORMAT_SEC));
        map2.put("date",AviatorEvaluator.exec("date_to_string(dd,\"yyyy-mm-dd\")",new Date()));
        System.out.println(AviatorEvaluator.execute("string_to_date('2018-09-11',\"yyyymmdd\")"));//Tue Jan 09 00:00:00 CST 2018
        System.out.println(AviatorEvaluator.exec("date_to_string(dd,\"yyyy-mm-dd\")",new Date()));//2017-18-30

        // map2.put("date1", "dd");
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("list",list);
        map3.put("nums",nums);
        map3.put("map2",map2);

        System.out.println(AviatorEvaluator.execute("list[0]+' '+list[1]",map3));//hello world
        System.out.println(AviatorEvaluator.execute("'nums[0]+nums[1]+nums[2]='+(nums[0]+nums[1]+nums[2])",map3));//nums[0]+nums[1]+nums[2]=3
        System.out.println(AviatorEvaluator.execute("'当前时间为：'+map2.date",map3));//当前时间为：2017-12-30 19:56:42

    }
}
