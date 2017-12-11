package com.test.jdbc;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static com.test.PropertiesUtil.DB_PATH;

public class DBConnection {
    private static DataSource dataSource;
    private static Connection connection;
    public DBConnection(){}
    public static Connection getConnection(){
        try {
            connection = dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;
    }
    static {
        FileInputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = new FileInputStream(DB_PATH);
            properties.load(inputStream);
            dataSource = BasicDataSourceFactory.createDataSource(properties);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
}
