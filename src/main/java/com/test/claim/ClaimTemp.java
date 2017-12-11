package com.test.claim;


import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.test.PropertiesUtil.CLAIM_XML_PATH;
import static com.test.PropertiesUtil.ESTIMATE_CLAIM_XML_PATH;


public class ClaimTemp {


    public  String readXml() throws IOException {

        InputStream inputStream = null;
        String filePath = CLAIM_XML_PATH;
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String aline = null;
            while((aline = bufferedReader.readLine())!=null ){
                sb.append(aline).append( "\n");
            }
        } finally  {
            if(inputStream!= null){
                inputStream.close();
            }
        }
      System.out.println(sb.toString()) ;
       return  sb.toString();
    }
    public  String readXml_estimate() throws IOException {

        InputStream inputStream = null;
        String filePath = ESTIMATE_CLAIM_XML_PATH;
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String aline = null;
            while((aline = bufferedReader.readLine())!=null ){
                sb.append(aline).append( "\n");
            }
        } finally  {
            if(inputStream!= null){
                inputStream.close();
            }
        }
        System.out.println(sb.toString()) ;
        return  sb.toString();
    }
    public String getClaimXml(String userAccount,String accidentNo, String claimNo,String claimCompanyId, String lossVehicleType ,String vin)throws IOException{
          String strXML = readXml();
          String claimTemplate  =  strXML.replace("=claimCompanyId=",claimCompanyId)
                  .replace("=accidentNo=",accidentNo)
                  .replace("=estimator=",userAccount)
                  .replace("=claimNo=",claimNo)
                  .replace("=lossVehicleType=",lossVehicleType)
                  .replace("=vin=",vin);


        return claimTemplate;
    }
    public String getClaimXml_estimate(String userAccount,String accidentNo, String claimNo,String claimCompanyId, String lossVehicleType ,String vin)throws IOException{
        String strXML = readXml_estimate();
        String claimTemplate  =  strXML.replace("=claimCompanyId=",claimCompanyId)
                .replace("=accidentNo=",accidentNo)
                .replace("=estimator=",userAccount)
                .replace("=claimNo=",claimNo)
                .replace("=lossVehicleType=",lossVehicleType)
                .replace("=vin=",vin);


        return claimTemplate;
    }



}
