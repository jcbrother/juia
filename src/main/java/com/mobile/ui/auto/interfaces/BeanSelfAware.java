package com.mobile.ui.auto.interfaces;

/**
 * Created by Jc on 17/1/21.
 */
public interface BeanSelfAware {

    //设定代理到类内部，嵌套调用时使用代理进行方法调用
    void setSelf(Object obj);

}
