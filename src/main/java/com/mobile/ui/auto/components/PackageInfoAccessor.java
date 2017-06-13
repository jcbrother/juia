package com.mobile.ui.auto.components;

import com.mobile.ui.auto.interfaces.CapabilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Jc on 16/8/6.
 * 获取测试程序包信息工具类
 * 该实例只能从spring容器中获取
 */

@Component
public class PackageInfoAccessor {

    @Autowired
    private PropertiesCenter propertiesCenter;

    private String packageDir = System.getProperty("user.dir") + String.format("%spackages", File.separator);
    private HashMap<String,String> packInfo = new HashMap<String,String>();

    public HashMap<String,String> getPackageInfo() throws IOException {
        String mobileOSType = propertiesCenter.getRunConfigs().get("mobile.os.type");
        String packageName = propertiesCenter.getRunConfigs().get("package.name");
        String packageVersion = propertiesCenter.getRunConfigs().get("package.version");
        String appUrl = "";
        if ("android".equalsIgnoreCase(mobileOSType)) {
            appUrl = packageDir + String.format("%s%s%s%s", File.separator, "Android", File.separator, packageName);
        } else {
            appUrl = packageDir + String.format("%s%s%s%s", File.separator, "iOS", File.separator, packageName);
        }
        packInfo.put(CapabilityType.APP_PATH, appUrl);
        packInfo.put(CapabilityType.PACKAGE_VERSION, packageVersion);
        return packInfo;
    }
}
