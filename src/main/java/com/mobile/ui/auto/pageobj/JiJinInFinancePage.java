package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/9/25.
 */
public class JiJinInFinancePage extends BaseMobilePage {

    private String pageName = "理财基金页面";
    private String pageDescription = "投资理财-基金";

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

    public JiJinInFinancePage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
