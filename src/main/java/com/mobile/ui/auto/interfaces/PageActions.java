package com.mobile.ui.auto.interfaces;

/**
 * Created by Jc on 16/8/7.
 */
public interface PageActions {

    //上滑
    void swipeUp();

    //下滑
    void swipeDown();

    //左滑
    void swipeLeft();

    //右滑
    void swipeRight();

    //iOS右滑返回
    void swipeForBack();

    //id定位点击
    void shortTapById(String elemId);

    //id定位长按
    void longTapById(String elemId);

    //id定位输入
    void sendKeysById(String elemId,String keyStr);

    //xpath定位点击
    void shortTapByXpath(String elemXpath);

    //xpath定位长按
    void longTapByXpath(String elemXpath);

    //xpath定位输入
    void sendKeysByXpath(String elemXpath,String keyStr);





}
