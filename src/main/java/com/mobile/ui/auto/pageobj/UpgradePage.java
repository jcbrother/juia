package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/10/23.
 */
public class UpgradePage extends BaseMobilePage{

    private String pageName = "升级引导页";
    private String pageDescription = "APP启动升级引导提示页";

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public UpgradePage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
