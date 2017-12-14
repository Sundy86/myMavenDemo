package com.test.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest1 {
    public static void main(String[] args) throws IOException {
        httpPostClient();
    }
    public static void httpGetClient() throws IOException {
        CloseableHttpClient httpclient =  HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response =httpclient.execute(get);

        StatusLine statusLine =response.getStatusLine();
        System.out.println(statusLine.getStatusCode());

        HttpEntity entity = response.getEntity();
        String ss = EntityUtils.toString(entity,"utf-8");

        System.out.println(ss);

        EntityUtils.consume(entity);
        httpclient.close();

    }
    public static  void httpPostClient()   {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://api.superepc.com/vtm/DataFunc");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("grant_code","XF9JKY0R"));
        list.add(new BasicNameValuePair("is_car_config","0"));
        list.add(new BasicNameValuePair("isCN","1"));
        list.add(new BasicNameValuePair("vinCode","LFV3A23C6D3406860"));

        HttpEntity httpEntity = null;
        try {
            HttpEntity  postEntity = new UrlEncodedFormEntity(list);
            post.setEntity(postEntity);

            CloseableHttpResponse postResponse =  httpClient.execute(post);
            httpEntity =  postResponse.getEntity();

            String ss = EntityUtils.toString(httpEntity,"utf-8");
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                EntityUtils.consume(httpEntity);
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
