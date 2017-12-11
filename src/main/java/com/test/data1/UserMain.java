package com.test.data1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class UserMain {
    private String username  ;
    private String password  ;
    User u;
    @DataProvider(name = "userInfo")
    public Object[][] getUserInfo(){
        List<Object> dataList = new ArrayList<Object>();
        User u1 = new User("lily1","1234");
        User u2 = new User("lily2","1235");
        User u3 = new User("lily3","1236");
        dataList.add(u1);
        dataList.add(u2);
        dataList.add(u3);
        Object [][] userData = new Object[dataList.size()][];
        for(int i=0 ;i<userData.length;i++){
            userData[i] = new Object[]{
                    dataList.get(i)
            };
        }
        return userData;
    }
    @Test (dataProvider = "userInfo")
    public void dataTest(User pa){
        System.out.println(pa.username);
    }
}
