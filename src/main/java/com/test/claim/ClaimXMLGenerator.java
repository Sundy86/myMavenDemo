package com.test.claim;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClaimXMLGenerator {


    private String accidentNo;
    private String claimNo;
    String strDate;
    String newClaimXml;
    int random;
    Date date;
    SimpleDateFormat sdf;

    public ClaimXMLGenerator() {
        date = new Date();
        sdf = new SimpleDateFormat("MMddhhmmssSSS");
        strDate = sdf.format(date);
        random = (int) Math.random() * 1000 + 1;

    }


    public String getUniqueAccidentNo(){
        accidentNo = "CCCIS_DEMO_ACC_" + strDate + random;
        return accidentNo;

    }

    public String getUniqueClaimNo(){
        claimNo = "CCCIS_DEMO_CLAIM_" + strDate + random;
        return claimNo;
    }

    public String getNewClaimXML() throws IOException{
        accidentNo= getUniqueAccidentNo();
        claimNo=getUniqueClaimNo();
        ClaimTemp ct = new ClaimTemp();
        newClaimXml = ct.getClaimXml("sc",accidentNo,claimNo,"2345","01","VF7N0RFN18AF11679");

        return newClaimXml;
    }
    public String getNewClaimXML_estimate() throws IOException{
        accidentNo= getUniqueAccidentNo();
        claimNo=getUniqueClaimNo();
        ClaimTemp ct = new ClaimTemp();
        newClaimXml = ct.getClaimXml_estimate("ssc",accidentNo,claimNo,"2345","01","VF7N0RFN18AF11679");

        return newClaimXml;
    }


}
