package com.mobile.ui.auto.components;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Jc on 16/8/6.
 * 从配置文件获取当前连接设备信息工具类
 * 该实例只能从spring容器中获取
 */

@Component
public class DeviceInfoAccessor {

    @Autowired
    private PropertiesCenter propertiesCenter;

    public JSONObject getDeviceInfo() throws IOException {
        HashMap<String, String> runConf = propertiesCenter.getRunConfigs();
        HashMap<String, JSONObject> devConf = propertiesCenter.getDeviceConfigs();
        String devName = runConf.get("mobile.device");
        JSONObject devInfo = devConf.get(devName);
        return devInfo;
    }

}
