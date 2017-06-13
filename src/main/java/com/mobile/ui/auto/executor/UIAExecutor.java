package com.mobile.ui.auto.executor;

import com.mobile.ui.auto.controller.ExecutorEngine;
import javassist.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jc on 16/8/7.
 */
public class UIAExecutor {

    public static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, NotFoundException, InvocationTargetException {
        ExecutorEngine caseExecutor = ((ExecutorEngine) ctx.getBean("executorEngine"));
        caseExecutor.execute();
    }
}
