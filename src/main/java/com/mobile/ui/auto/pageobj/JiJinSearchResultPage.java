package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/9/25.
 */
public class JiJinSearchResultPage extends BaseMobilePage {

    private String pageName = "基金搜索结果页面";
    private String pageDescription = "投资理财-基金-搜索-确认搜索-结果";

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

    public JiJinSearchResultPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
