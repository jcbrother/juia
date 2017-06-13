package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/10/23.
 */
public class DingQiList extends BaseMobilePage{

    private String pageName = "定期频道";
    private String pageDescription = "定期频道类目列表页";

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

    public DingQiList initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
