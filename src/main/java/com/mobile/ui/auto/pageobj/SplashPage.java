package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/9/25.
 */
public class SplashPage extends BaseMobilePage {

    private String pageName = "启动图页面";
    private String pageDescription = "首次启动-启动图";

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

    public SplashPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
