package com.test.example;


import java.io.*;

public class hyq {

    public int sumNum(int a, int b)
    {
        return a + b;
    }

    public int add(){
        int num=0;
        String tempString = null;
        File file = new File("d://email.txt");
        BufferedReader reader = null;
        String str;
        String str3 ="";
        String str2 ="";
        try{
            reader = new BufferedReader(new FileReader(file));
            tempString = reader.readLine();
            reader.close();
            str =tempString.trim();
            if(tempString !=null && !"".equals(str)){
                for (int i=0;i<str.length();i++){
                    if(str.charAt(i)>=48 && str.charAt(i)<=57){
                        str2+=str.charAt(i);
                    }
                }
                System.out.println("读出文件的字符串为："+str);
                System.out.println("读出文件的数字为："+str2);
                num=Integer.parseInt(str2);
                System.out.println("把文件中的数字转换成INT型："+num);
                num = num +1;
                str3 =String.valueOf(num);

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    String str4 = str3+"@qq.com";
        try{
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str3);
            bw.close();
            System.out.println("done");
        }catch(IOException e){
            e.printStackTrace();
        }
        return num;
    }
}
