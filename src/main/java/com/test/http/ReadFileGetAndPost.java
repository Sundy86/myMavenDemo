package com.test.http;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class ReadFileGetAndPost {
    public static void main(String[] args) throws HttpClientException, IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;
        File file = new File(filePath + "data.txt");
        File file1 = new File(filePath + "result.txt");
        String ret = "";
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        for (int m = 0; m < lines.size(); m++) {
            if (m > 0) {
                String[] ss = lines.get(m).split(";");
                String url = ss[0];
                String method = ss[1];
                if ("get".equals(method)) {
                    ret = HttpUtils.doGet(url);
                    writeTofile(file1, "\nget ------>" + ret);
                 } else if ("post".equals(method)) {
                    ret = HttpUtils.doPost(url, ss[2], "&");
                    writeTofile(file1, "\npost ------>" + ret);
                }
            }
        }
    }
    public static void writeTofile(File file,String str) throws IOException {
        FileUtils.writeStringToFile(file,str,"utf-8",true);

    }
}
