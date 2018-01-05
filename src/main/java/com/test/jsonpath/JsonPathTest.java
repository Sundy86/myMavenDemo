package com.test.jsonpath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonPathTest {
    public static void main(String[] args) throws IOException {
        String path =System.getProperty("user.dir")+File.separator+"testdata"+File.separator+"test.json";
        String json;
        try {
            json = FileUtils.readFileToString(new File(path), "utf-8");
            ReadContext context = JsonPath.parse(json);
            //���book[1]��authorֵ
            //����һ
            String author = context.read("$.store.book[1].author");
            //������
            String author1 = context.read("$['store']['book'][1]['author']");
            System.out.println("$.store.book[1].author = "+author+"-------\n"+author1);//$.store.book[1].author = Evelyn Waugh
            //���book[*]��category == 'reference'��book
            List<Object> categorys = context.read("$.store.book[?(@.category == 'reference')]");
            for(Object st: categorys){
                System.out.println(st.toString());
                //{category=reference, author=Nigel Rees, title=Sayings of the Century, price=8.95}
            }

            //���book[*]��price>10��book
            List<Object> prices = context.read("$.store.book[?(@.price>10)]");
            for(Object p:prices){
                System.out.println(p.toString());
                //{category=fiction, author=Evelyn Waugh, title=Sword of Honour, price=12.99, isbn=0-553-21311-3}
            }
            //bicycle[*]�к���colorԪ�ص�bicycle
            List<Object> color = context.read("$.store.bicycle[?(@.color)]");
            for(Object is :color){
                System.out.println(is.toString());//{color=red, price=19.95}
            }

            //�����json������price��ֵ
            List<Object> pp = context.read("$..price");
            for(Object p :pp){
                System.out.println(p.toString()); //8.95  12.99   19.95
            }

            List<String> authors = context.read("$.store.book[*].author");
            for (String str : authors) {
                System.out.println(str);//Nigel Rees     Evelyn Waugh
            }

            JsonPath jsonPath = JsonPath.compile("$.store.book[*]");
            List<Object> authors1 =  jsonPath.read(json);
            for (Object str : authors1) {
                System.out.println(str.toString());
                //{category=reference, author=Nigel Rees, title=Sayings of the Century, price=8.95}
                //{category=fiction, author=Evelyn Waugh, title=Sword of Honour, price=12.99, isbn=0-553-21311-3}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
