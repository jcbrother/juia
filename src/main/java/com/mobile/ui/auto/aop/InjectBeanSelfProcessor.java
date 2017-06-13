package com.mobile.ui.auto.aop;

import com.mobile.ui.auto.controller.ExecutorEngine;
import com.mobile.ui.auto.interfaces.BeanSelfAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Jc on 17/1/21.
 */
@Component
public class InjectBeanSelfProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ExecutorEngine)
        {
            System.out.println("Inject proxy：" + bean.getClass());
            BeanSelfAware myBean = (BeanSelfAware) bean;
            myBean.setSelf(bean);
            return myBean;
        }
        return bean;
    }

}
