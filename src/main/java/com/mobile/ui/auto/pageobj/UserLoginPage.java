package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/10/23.
 */
public class UserLoginPage extends BaseMobilePage{

    private String pageName = "用户登录";
    private String pageDescription = "用户登录页面";

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

    public UserLoginPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
