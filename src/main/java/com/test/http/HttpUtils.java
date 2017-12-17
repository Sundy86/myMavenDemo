package com.test.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Suncy on 2017/12/17 0017.
 */
public class HttpUtils {
    private static CloseableHttpClient httpclient;

    static {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(200); //连接池最大并发连接数
        manager.setDefaultMaxPerRoute(200);//单路由最大并发数,路由是对maxTotal的细分
        httpclient = HttpClients.custom().setConnectionManager(manager).build();
    }

    /* ConnectionRequestTimeout httpclient使用连接池来管理连接，这个时间就是从连接池获取连接的超时时间，可以想象下数据库连接池
       ConnectTimeout 建立连接最大时间
       SocketTimeout 数据传输过程中数据包之间间隔的最大时间
       HttpHost 代理
     */
    private static RequestConfig config =RequestConfig.copy(RequestConfig.DEFAULT)
            .setSocketTimeout(10000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(100).build();
           // .setProxy(new HttpHost("127.0.0.1",8888,"http")).build();

    public static String doGet(String url, Map<String, Object> header)
            throws HttpClientException {
        String ret = "";
        HttpGet get = new HttpGet(url);
        get.setConfig(config);
        get.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            if (header != null) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    get.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            closeableHttpResponse = httpclient.execute(get);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            } else {
                throw new HttpClientException(
                        "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode() + "].");
            }
        } catch (ClientProtocolException e) {
            throw new HttpClientException("HttpClient error," + e.getMessage());
        } catch (IOException e) {
            throw new HttpClientException("IO error," + e.getMessage());
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }

    public static String doGet(String url) throws HttpClientException {
        return doGet(url,null);
    }

    public static String doPost(String url, Map<String, Object> params, Map<String, Object> header)
            throws HttpClientException {
        String ret = "";
        HttpPost post = new HttpPost(url);
        post.setConfig(config);
        post.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        CloseableHttpResponse closeableHttpResponse = null;
        HttpEntity postEntity = null;
        try {
            if(params!=null) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                postEntity = new UrlEncodedFormEntity(list);
                post.setEntity(postEntity);
            }

            if (header != null) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            closeableHttpResponse = httpclient.execute(post);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            } else {
                throw new HttpClientException(
                        "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode() + "].");
            }
        } catch (ClientProtocolException e) {
            throw new HttpClientException("HttpClient error," + e.getMessage());
        } catch (IOException e) {
            throw new HttpClientException("IO error," + e.getMessage());
        } finally {
            if(postEntity!=null) {
                try {
                    EntityUtils.consume(postEntity);
                } catch (IOException e) {
                }
            }
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }
    public static String doPost(String url, Map<String, Object> params) throws HttpClientException {
        return doPost(url,params,null);
    }
    public static void main(String[] args) {
        String result = null;
        try {
            result = doGet("http://123.58.251.183:8080/goods/UserServlet?method=loginMobile&loginname=test1&loginpass=test1");
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
       //{"msg":"登录成功","uid":"587038694C1C415DB0FA35C1AC6DE12D","code":"1"}
        //JSONObject 的方式转化JSON字符串
        JSONObject object = (JSONObject) JSON.parse(result);
        System.out.println("JSONObject  uid  = "+object.get("uid"));

        //Map 的方式转化JSON字符串
        Map<String,String> stringStringMap = (Map<String,String>) JSON.parse(result);
        System.out.println("uid  = "+stringStringMap.get("uid"));
        for (String s : stringStringMap.keySet()) {
            System.out.println(s + "==>" +stringStringMap.get(s));
        }

        //对象 的方式转化JSON字符串
        LoginInfo loginInfo = JSON.parseObject(result,LoginInfo.class);
        System.out.println("Object uid  = "+loginInfo.getUid());

//        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("method", "loginMobile");
//        params.put("loginname", "test1");
//        params.put("loginpass", "test1");
//
//        try {
//            result = doPost("http://123.58.251.183:8080/goods/UserServlet", params);
//        } catch (HttpClientException e) {
//            e.printStackTrace();
//        }
        System.out.println(result);
    }


}
