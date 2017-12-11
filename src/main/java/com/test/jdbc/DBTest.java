package com.test.jdbc;

import com.test.Log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.assertj.db.type.Request;
import org.testng.annotations.Test;
import static org.assertj.db.api.Assertions.assertThat;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class DBTest {
    private static Log log = new Log(DBTest.class);


    public static Request getAllSelectResult(String selectSql){
        log.info("select all sql is :"+selectSql);
        Request request = new Request(DBConnection.getDataSource(),selectSql);
        return request;
    }
    public static  Request getPartSelectResult(String str,String selectSql){
        log.info("在 " +str+" 数据库中，提取数据...");
        log.info(" select part sql is :"+selectSql);
        Request request = new Request(DBConnection.getDataSource(),selectSql);

         return request;
    }
    public static Map getSingleResultSet(String sql) {
       // Map<String, Object> map = null;
        Map map = null;
        QueryRunner queryRunner = new QueryRunner(DBConnection.getDataSource());
        try {
            map = queryRunner.query(sql, new MapHandler(), (Object[]) null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public  static void updateSQL(String sql){
        QueryRunner queryRunner = new QueryRunner(DBConnection.getDataSource());
        try {
            log.info("sql is :"+sql);
            int m = queryRunner.update(sql);
            log.info("第"+m+"行更新成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DBqueryTest() {
        int maxRetryTime = 50;
        Boolean b = false;
        String sql = "select t.claim_id from t_claim t where t.accident_no='acc_20170718_03'";
        Map  sql_result;

        outer:
        for (int i = 1; i <= maxRetryTime; i++) {
            sql_result = getSingleResultSet(sql);
            if (!(sql_result == null || sql_result.size() == 0)) {
                Iterator<Map.Entry<String, Object>> it = sql_result.entrySet().iterator();
                Map.Entry<String, Object> e = it.next();
                System.out.println("定损单ID：" + e.getValue());

                if (e.getValue() != null) {
                    b = true;
                    this.sleep();
                    break outer;
                }
            }
            this.sleep();
        }
    }
    @Test
    public void DBupdateTest(){

        String sql1 ="update t_acc_loss_info f set f.damage_site='我是新的出险地点sc111' where f.accident_no='APD_acc_09201048541'";
        String sql2 ="select * from t_acc_loss_info f where f.accident_no='APD_acc_09201048541'";
        updateSQL(sql1);
        Request rs = getAllSelectResult(sql2);

      assertThat(rs).column("damage_site").value().isEqualTo("我是新的出险地点sc111");
     }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
