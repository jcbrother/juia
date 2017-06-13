package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/10/23.
 */
public class MyAccountPage extends BaseMobilePage{

    private String pageName = "我的账户";
    private String pageDescription = "我的账户页面";

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

    public MyAccountPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
