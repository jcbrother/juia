package com.mobile.ui.auto.pageobj;

import io.appium.java_client.AppiumDriver;

/**
 * Created by Jc on 16/10/23.
 */
public class CFHCHListPage extends BaseMobilePage{

    private String pageName = "财富汇彩虹三级列表";
    private String pageDescription = "定期频道财富汇彩虹三级列表页";

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

    public CFHCHListPage initOprDriver(AppiumDriver driver){
        super.bindToDriver(driver);
        return this;
    }

}
