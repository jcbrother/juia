package com.mobile.ui.auto.interfaces;

/**
 * Created by Jc on 16/8/6.
 */
public interface CapabilityType {

    String PLATFORM_NAME = "platformName";
    String PLATFORM_VERSION = "platformVersion";
    String DEVICE_NAME = "deviceName";
    String UDID = "udid";
    String APP_PATH = "app";
    String PACKAGE_VERSION = "packageVersion"; //该属性不在setCapabilities()中使用
    String NO_RESET = "noReset";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";

}
