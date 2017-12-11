package com.test.claim;

import com.test.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.test.PropertiesUtil.ESTIMATE_PUSHCLAIM_URL;

public class PushClaim_estimate {
    private static Log log = new Log(PushClaim_estimate.class);
    ClaimXMLGenerator cx = new ClaimXMLGenerator();

    //@Test( threadPoolSize=2, invocationCount=3)
    @Test
    public void test() throws IOException, InterruptedException {
        importClaimTask1();
    }

    public void importClaimTask1() throws IOException, InterruptedException {
        String soapRequestData = cx.getNewClaimXML_estimate();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://192.168.200.14:8001/web-suite/services/template/claimPushWS");
        StringEntity myEntity2 = new StringEntity(soapRequestData.toString(), "UTF-8");
        myEntity2.setContentType("application/soap+xml;charset=UTF-8");
        post.setEntity(myEntity2);
        HttpResponse response = client.execute(post);
       System.out.println(response.getStatusLine().getStatusCode()+"~~~~~~~~~~~~~~~~");
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String result = EntityUtils.toString(resEntity, "UTF-8");
            log.info(result);
        } else {
            log.info(soapRequestData);
            log.info("请求失败");
        }
        Thread.sleep(2000);
    }

    public String getAccidentNo() throws IOException, InterruptedException  {
        importClaimTask1();
        String accidentNo = cx.getUniqueAccidentNo();
        return accidentNo;
    }

}
