package com.mobile.ui.auto.services;

import com.mobile.ui.auto.components.DeviceInfoAccessor;
import com.mobile.ui.auto.components.PackageInfoAccessor;
import com.mobile.ui.auto.components.PropertiesCenter;
import com.mobile.ui.auto.interfaces.CapabilityType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 根据获取到的设备信息生成Appium驱动
 * Created by jiangcan on 16/8/4.
 */

@Scope("singleton")
@Service
public class DriverGeneratorService {

    @Autowired
    public DeviceInfoAccessor deviceInfoAccessor;
    @Autowired
    public PackageInfoAccessor packageInfoAccessor;
    @Autowired
    public PropertiesCenter propertiesCenter;

    public DesiredCapabilities capabilities = new DesiredCapabilities();
    public JSONObject devInfo = null;
    public String appPath = null;
    public boolean isNoResetApp = false;

    public DriverGeneratorService setLuCapabilities() throws IOException {
        devInfo = deviceInfoAccessor.getDeviceInfo();
        isNoResetApp = Boolean.parseBoolean(propertiesCenter.getRunConfigs().get("app.no.reset"));
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, devInfo.getString(CapabilityType.PLATFORM_NAME));
        capabilities.setCapability(CapabilityType.PLATFORM_VERSION, devInfo.getString(CapabilityType.PLATFORM_VERSION));
        capabilities.setCapability(CapabilityType.DEVICE_NAME, devInfo.getString(CapabilityType.DEVICE_NAME));
        capabilities.setCapability(CapabilityType.UDID, devInfo.getString(CapabilityType.UDID));
        capabilities.setCapability(CapabilityType.NO_RESET, isNoResetApp);
//      capabilities.setCapability("unicodeKeyboard", "True");   输入中文
//      capabilities.setCapability("resetKeyboard", "True");     输入中文
        if(isNoResetApp == true){
            capabilities.setCapability(CapabilityType.APP_PACKAGE,"com.lufax.android");
//            capabilities.setCapability(CapabilityType.APP_ACTIVITY,"com.lufax.android.activity.HomeActivity");
            capabilities.setCapability(CapabilityType.APP_ACTIVITY,"com.lufax.android.activity.WelcomeActivity");
        }else{
            appPath = packageInfoAccessor.getPackageInfo().get(CapabilityType.APP_PATH);
            capabilities.setCapability(CapabilityType.APP_PATH, appPath);
        }
        return this;
    }

    public AppiumDriver getAppiumDriver() throws MalformedURLException {
        if("android".equalsIgnoreCase(this.capabilities.getCapability(CapabilityType.PLATFORM_NAME).toString())){
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), this.capabilities);
        }else{
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),this.capabilities);
        }
    }
}