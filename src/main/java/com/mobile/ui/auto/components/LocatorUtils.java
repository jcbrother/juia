package com.mobile.ui.auto.components;

import com.mobile.ui.auto.controller.ExecutorEngine;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Jc on 17/2/8.
 * 定位元素工具类
 */

@Component
public class LocatorUtils {

    @Autowired
    public ExecutorEngine executorEngine;

    public AppiumDriver oprDriver;

    @PostConstruct
    public void init(){
        oprDriver = executorEngine.getOprDriver();
    }


    public WebElement locateElementById(String id){
        WebElement element = oprDriver.findElementById(id);
        return element;
    }

    public WebElement locateElementByXpath(String xpath){
        WebElement element = oprDriver.findElementByXPath(xpath);
        return element;
    }

    public WebElement locateElement(String locationType, String locator){
        if(locationType.equals("id")){
            return locateElementById(locator);
        }else if(locationType.equals("xpath")){
            return locateElementByXpath(locator);
        }else {
            return null;
        }
    }

}
