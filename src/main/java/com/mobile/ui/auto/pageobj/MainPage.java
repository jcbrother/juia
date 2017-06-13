package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/9/25.
 */
public class MainPage extends BaseMobilePage {

    private String pageName = "首页";
    private String pageDescription = "非首次启动-首页";

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

    public MainPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
