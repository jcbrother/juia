package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/10/23.
 */
public class FinanceListPage extends BaseMobilePage{

    private String pageName = "投资理财列表页";
    private String pageDescription = "投资理财大列表页";

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

    public FinanceListPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
