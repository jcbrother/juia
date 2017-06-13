package com.mobile.ui.auto.components;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * Created by jiangcan on 16/8/4.
 * 获取属性文件信息工具类
 * 该实例只能从spring容器中获取
 */

@Component
public class PropertiesFetcher {

    private InputStream runConfigFileStream;
    private InputStream deviceConfigFileStream;
    private InputStream dbConfigFileStream;

    private HashMap<String,String> runConfigs = new HashMap<String,String>();
    private HashMap<String,JSONObject> deviceConfigs = new HashMap<String, JSONObject>();
    private HashMap<String,JSONObject> dbConfigs = new HashMap<String, JSONObject>();


    public PropertiesFetcher() {
        runConfigFileStream = getClass().getClassLoader().getResourceAsStream("run_config.properties");
        deviceConfigFileStream = getClass().getClassLoader().getResourceAsStream("device_config.properties");
        dbConfigFileStream = getClass().getClassLoader().getResourceAsStream("db_config.properties");
    }

    //获取运行配置信息
    public void fetchIntoRunConfigs() throws IOException {
        Properties props = loadPropertiesFromStream(runConfigFileStream);
        Set<String> propNames = props.stringPropertyNames();
        Iterator<String> it = propNames.iterator();
        while(it.hasNext()){
            String key = it.next();
            String value = props.getProperty(key);
            runConfigs.put(key,value);
        }
        runConfigFileStream.close();
    }

    //获取设备信息
    public void fetchIntoDeviceConfigs() throws IOException {
        Properties props = loadPropertiesFromStream(deviceConfigFileStream);
        Set<String> propNames = props.stringPropertyNames();
        Iterator<String> it = propNames.iterator();
        while(it.hasNext()){
            String key = it.next();
            String stringValue = props.getProperty(key);
            JSONObject jsonValue = new JSONObject(stringValue);
            deviceConfigs.put(key,jsonValue);
        }
        deviceConfigFileStream.close();
    }

    //获取数据库配置信息
    public void fetchIntoDBConfig() throws IOException {
        Properties props = loadPropertiesFromStream(dbConfigFileStream);
        Set<String> propNames = props.stringPropertyNames();
        Iterator<String> it = propNames.iterator();
        while(it.hasNext()){
            String key = it.next();
            String stringValue = props.getProperty(key);
            JSONObject jsonValue = new JSONObject(stringValue);
            dbConfigs.put(key,jsonValue);
        }
        dbConfigFileStream.close();
    }

    public void fetchAllConfigs() throws IOException {
        fetchIntoRunConfigs();
        fetchIntoDeviceConfigs();
        fetchIntoDBConfig();
    }


    public Properties loadPropertiesFromStream(InputStream fileStream) throws IOException {
        Properties props = new Properties();
        props.load(fileStream);
        return props;
    }

    public HashMap<String, String> getRunConfigs() {
        return runConfigs;
    }

    public HashMap<String, JSONObject> getDeviceConfigs() {
        return deviceConfigs;
    }

    public HashMap<String, JSONObject> getDbConfigs() {
        return dbConfigs;
    }
}
