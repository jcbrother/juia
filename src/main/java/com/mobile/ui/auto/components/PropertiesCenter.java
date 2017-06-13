package com.mobile.ui.auto.components;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Jc on 16/9/11.
 */

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PropertiesCenter {

    @Autowired
    private PropertiesFetcher propertiesFetcher;

    private HashMap<String,String> runConfigs = null;
    private HashMap<String,JSONObject> deviceConfigs = null;
    private HashMap<String,JSONObject> dbConfigs = null;

    @PostConstruct
    public void init() throws IOException {
        if (runConfigs == null && deviceConfigs == null && dbConfigs == null) {
            propertiesFetcher.fetchAllConfigs();
            this.runConfigs = propertiesFetcher.getRunConfigs();
            this.deviceConfigs = propertiesFetcher.getDeviceConfigs();
            this.dbConfigs = propertiesFetcher.getDbConfigs();
        }
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
