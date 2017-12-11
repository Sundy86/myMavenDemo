package com.test.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XMLReadTest {
    public static XMLConfiguration xmlConfig = null;

    static {
        try {
            xmlConfig = new XMLConfiguration("config.xml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String xmlRead(String key) {
        String str = xmlConfig.getString(key);
        return str;
    }

    public static String xmlReads(String key) {

        List<Object> strs = xmlConfig.getList(key);
        return Arrays.toString(strs.toArray());

    }

    public static void readXML() {

        // XMLConfiguration xmlConfig = new XMLConfiguration("config.xml");

        //对于单独的元素，可以直接通过元素名称获取值
        String boy = xmlConfig.getString("boy");
        System.out.println("boy:\t" + boy);//boy:	tom

        //对于不存在的元素，Configuration会自动添加并赋值
        String sex = xmlConfig.getString("sex", "女");
        System.out.println("sex:\t" + sex);//sex:	女

        //对于循环出现的嵌套元素，可以通过父元素.子元素来获取集合值
        List<Object> names = xmlConfig.getList("student.name");
        System.out.println(Arrays.toString(names.toArray()));//[lily, lucy]

        //对于单独的元素包含的值有多个的话如：a,b,c,d 可以通过获取集合
        List<Object> title = xmlConfig.getList("title");
        System.out.println(Arrays.toString(title.toArray()));//[abc, cbc, bbc, bbs]

        //标签元素读取，使用 标签[@属性名] 的方式来获取
        String size = xmlConfig.getString("ball[@size]");
        System.out.println("ball[@size]:\t" + size);//20

        //嵌套标签中的元素读取，使用 标签(第几个)[@属性名] 的方式来获取,其中”第几个‘为下标，从0开始
        String stu_id = xmlConfig.getString("student(1)[@id]");
        System.out.println("student(1)[@id]:\t" + stu_id);//2

        //对于标签里面的属性名称读取，使用 标签(第几个).标签[@属性名]
        String stu_name_go1 = xmlConfig.getString("student(1).name[@go]");
        System.out.println("student(1).name[@go]:\t" + stu_name_go1);//common2

        //对于标签里面的属性名称读取，也可以使用 标签.标签（第几个）[@属性名]
        String stu_name_go2 = xmlConfig.getString("student.name(0)[@go]");
        System.out.println("student.name(0)[@go]:\t" + stu_name_go2);//common1
        System.out.println("------------------------------------");
        Iterator<String> itr=xmlConfig.getKeys(); //获取当前XMLConfiguration对象可以使用的key
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        /*
        *   boy
            student.name
            student.name[@go]
            student.age
            student[@id]
            title
            ball
            ball[@size]
        *
        * */
        System.out.println("------------------------------------");
    }

    public static void main(String args[]) {
        readXML();
        System.out.println("boy:\t" + xmlRead("Students"));//boy:	tom
        System.out.println(xmlReads("student.name"));//[lily, lucy]
    }
}
