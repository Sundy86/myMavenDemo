package com.test.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class FileUtilsTest {
    public static void main(String args[]) throws IOException {
       // operationFile();
        //清空指定目录下的所有文件
        //FileUtils.cleanDirectory(new File("c:/target"));
        //判断文件是否存在
        System.out.println(FileUtils.directoryContains(new File("d:/source"),new File("d:/source/pushClaim.xml")));

    }
    public  static  void readFile() throws IOException {
        //读取目标文件，内部调用IOUtils.toString(inputstream,"gbk)
        // System.out.println(FileUtils.readFileToString(new File("d:/source/pushClaim.xml"),"gbk"));
        byte[] bytes = FileUtils.readFileToByteArray(new File("d:/source/pushClaim.xml"));
        String s = new String(bytes,"gbk");
        // System.out.println(s);

        List<String> list = FileUtils.readLines(new File("d:/source/pushClaim.xml"),"gbk");
        for(String l : list){
            System.out.println(l);
        }
    }
    public  static void writeFile() throws IOException {
        //四个参数分别为：目标文件，写入的字符串，字符集，是否追加
        FileUtils.writeStringToFile(new File("d:/source/pushClaim.xml"),"我是被写入的内容","",true);

        //write可以接受charsequence类型的数据，string,stringbuilder和stringbuffer都是实现了charsequence接口
        FileUtils.write(new File("d:/source/pushClaim.xml"),"target char sequence","utf-8",true);

        FileUtils.writeByteArrayToFile(new File("d:/source/pushClaim.xml"),"bytes".getBytes());//(file,字符数组)
        FileUtils.writeByteArrayToFile(new File("d:/source/pushClaim.xml"),"bytes".getBytes(),true);//(file,字符数组，是否追加)
        FileUtils.writeByteArrayToFile(new File("d:/source/pushClaim.xml"),"bytes".getBytes(),0,10);//(file,字符数组，起始位置，结束位置)
        FileUtils.writeByteArrayToFile(new File("d:/source/pushClaim.xml"),"bytes".getBytes(),0,10,true);//(file,字符数组，起始位置，结束位置，是否追加)

        //writeLines多了一个lineEnding参数
        FileUtils.writeLines(new File("d:/source/pushClaim.xml"),"utf-8", FileUtils.readLines(new File("d:/source/pushClaim.xml"),"utf-8"));
    }
    public static void findFiles(){
        File parent = new File("D://test");
        //返回文件的列表
        List<File> files = (List<File>) FileUtils.listFiles(parent,new String[]{"test1","test2"},true);
        //返回文件迭代器
        Iterator<File> files_iter =  FileUtils.iterateFiles(parent,new String[]{"test1","test3"},true);
        //把collection<File>转换成File[]
        FileUtils.convertFileCollectionToFileArray(files);
    }
    public static void getDirectory(){
        //获取临时目录
        System.out.println("获取临时目录"+FileUtils.getTempDirectory());//C:\Users\ccc\AppData\Local\Temp
        System.out.println("获取临时目录"+FileUtils.getTempDirectoryPath());//C:\Users\ccc\AppData\Local\Temp\
        //获取用户主目录
        System.out.println("获取用户主目录"+FileUtils.getUserDirectory());//C:\Users\ccc
        System.out.println("获取用户主目录"+FileUtils.getUserDirectoryPath());//C:\Users\ccc

        //以可读的方式，返回文件的大小EB, PB, TB, GB, MB, KB or bytes
        System.out.println(FileUtils.byteCountToDisplaySize(1000000));//976 KB
        System.out.println(FileUtils.byteCountToDisplaySize(1));//1 bytes

        String path2=System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test1.xml";
        String path3=System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test.xml";
        try {
            //创建文件，如果文件存在则更新时间；如果不存在，创建一个空文件
            FileUtils.touch(new File(path2));
            //文件内容的对比
            System.out.println(FileUtils.contentEquals(new File(path2),new File(path3)));
            //忽略换行符，第三个参数是字符集
            System.out.println(FileUtils.contentEqualsIgnoreEOL(new File(path2),new File(path3),"UTF-8"));



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void operationFile() throws IOException {
//        FileUtils.copyDirectory(new File("D:\\test"),new File("C:\\test"));
//        // 创建包含目录或者txt文件的过滤器
//        IOFileFilter txtfileFilter =  FileFilterUtils.suffixFileFilter(".txt");
//        IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtfileFilter);
//        FileFilter filter = FileFilterUtils.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);
          //复制文件夹到指定路径，不复制.txt文件
//        FileUtils.copyDirectory(new File("D:\\test"),new File("C:\\test"),filter);
        File sourceDir= new File("D:\\source");
        File targetDir = new File("C:\\target");
        //仅仅拷贝目录
        FileUtils.copyDirectory(sourceDir,targetDir,DirectoryFileFilter.DIRECTORY);

        File sourcefile = new File("D:\\source\\pushClaim.xml");
        File targetfile = new File("C:\\target\\pc.xml");
        //将sourcefile中的文件内容移到targetfile的pc.xml文件中，source中的文件被移除，pc.xml是不存在的，移动时自动创健
       // FileUtils.moveFile(sourcefile,targetfile);
        //将sourcefile中的文件内容复制到targetfile的pc.xml文件中，pc.xml是不存在的，移动时自动创健
        File sourcefile1 = new File("D:\\source\\pushClaim.xml");
        File targetfile1 = new File("C:\\target\\pc1.xml");
       // FileUtils.copyFile(sourcefile1,targetfile1,true);


    }

    public static void writeORreadFile(){
        String path1=System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test.xml";
        try {
            FileInputStream inputStream = FileUtils.openInputStream(new File(path1));
            InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader read = new BufferedReader(reader);
            String line=null;
            while ((line=read.readLine())!=null){
                System.out.println(line);
            }
            read.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String path2=System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test.xml";
            //是否追加的形式添加内容
            FileOutputStream outputStream =FileUtils.openOutputStream(new File(path2),true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write("hello, this is FileUtils test ");
            outputStreamWriter.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
