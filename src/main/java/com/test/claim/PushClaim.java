package com.test.claim;

import com.test.Log;
import com.test.http.HttpClientException;
import com.test.http.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.test.PropertiesUtil.PUSHCLAIM_URL;

public class PushClaim {
    private static Log log = new Log(PushClaim.class);
    ClaimXMLGenerator cx = new ClaimXMLGenerator();

    //@Test( threadPoolSize=2, invocationCount=3)
    @Test
    public void test() throws IOException, InterruptedException {
        importClaimTask();
    }

    public void importClaimTask()  {
        try {
            String soapRequestData = cx.getNewClaimXML();
            String ret =  HttpUtils.doPostByType("soap+xml",PUSHCLAIM_URL,soapRequestData);
            System.out.println(ret);
        } catch (HttpClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAccidentNo() throws IOException, InterruptedException  {
        importClaimTask();
        String accidentNo = cx.getUniqueAccidentNo();
        return accidentNo;
    }

}
