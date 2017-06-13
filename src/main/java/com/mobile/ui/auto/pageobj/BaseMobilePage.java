package com.mobile.ui.auto.pageobj;

import com.mobile.ui.auto.interfaces.PageActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;

/**
 * Created by Jc on 16/8/7.
 * 移动端APP页面基础类
 */

public class BaseMobilePage implements PageActions {

    private AppiumDriver driver = null;
    private int pageWidth = 0;
    private int pageHeight = 0;
    private int pageSwipeDuration = 1000;
    private int longTapDuration = 2000;


    //设置页面对象属性

    public BaseMobilePage setDriver(AppiumDriver driver) {
        if(this.driver == null) {
            this.driver = driver;
        }
        return this;
    }

    public BaseMobilePage setPageSize(){
        this.pageWidth = driver.manage().window().getSize().width;
        this.pageHeight = driver.manage().window().getSize().height;
        return this;
    }

    public BaseMobilePage bindToDriver(AppiumDriver driver){
        this.setDriver(driver);
        this.setPageSize();
        return this;
    }

    //获取页面对象属性
    public WebElement getElementById(String elemId){
        return driver.findElementById(elemId);
    }

    public WebElement getElementByXpath(String elemXpath){
        return driver.findElementByXPath(elemXpath);
    }

    public AppiumDriver getDriver(){
        return driver;
    }

    public int getPageWidth(){
        return pageWidth;
    }

    public int getPageHeight(){
        return pageHeight;
    }

    //重写页面对象方法
    @Override
    public void swipeUp() {
        driver.swipe(pageWidth/2, pageHeight*3/4, pageWidth/2, pageHeight/4, pageSwipeDuration);
    }

    @Override
    public void swipeDown() {
        driver.swipe(pageWidth/2, pageHeight/4, pageWidth/2, pageHeight*3/4, pageSwipeDuration);
    }

    @Override
    public void swipeLeft() {
        driver.swipe(pageWidth*4/5, pageHeight/2, pageWidth/5, pageHeight/2, pageSwipeDuration);
    }

    @Override
    public void swipeRight() {
        driver.swipe(pageWidth / 5, pageHeight / 2, pageWidth * 4 / 5, pageHeight / 2, pageSwipeDuration);
    }

    @Override
    public void swipeForBack() {
        driver.swipe(0, pageHeight / 2, pageWidth * 4 / 5, pageHeight / 2, pageSwipeDuration);
    }

    @Override
    public void shortTapById(String elemId) {
        WebElement element = driver.findElementById(elemId);
        element.click();
    }

    @Override
    public void longTapById(String elemId) {
        TouchAction action = new TouchAction(driver);
        WebElement element = driver.findElementById(elemId);
        action.longPress(element,longTapDuration).perform();
    }

    @Override
    public void sendKeysById(String elemId,String keyStr) {
        WebElement element = driver.findElementById(elemId);
        element.sendKeys(keyStr);
    }

    @Override
    public void shortTapByXpath(String elemXpath) {
        WebElement element = driver.findElementByXPath(elemXpath);
        element.click();
    }

    @Override
    public void longTapByXpath(String elemXpath) {
        TouchAction action = new TouchAction(driver);
        WebElement element = driver.findElementByXPath(elemXpath);
        action.longPress(element, longTapDuration).perform();
    }

    @Override
    public void sendKeysByXpath(String elemXpath,String keyStr) {
        WebElement element = driver.findElementByXPath(elemXpath);
        element.sendKeys(keyStr);
    }


    /*
    测试代码函数
    public void testPrint1(String param1, Integer param2, int param3){
        System.out.println(param1 + param2 + param3 + ">>testPrint1");
    }

    public void testPrint2(String param1,int param2, Integer param3){
        System.out.println(param1 + param2 + param3 + ">>testPrint2");
    }

    public void testPrint3(Integer param1, int param2, String param3){
        System.out.println(param1+param2+param3 + ">>testPrint3");
    }

    public void testPrint4(Integer param1, String param2, int param3){
        System.out.println(param1+param2+param3 + ">>testPrint4");
    }
    */


}
