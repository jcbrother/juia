package com.mobile.ui.auto.executor;

import javassist.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jc on 16/8/7.
 */
public class Main {
    public static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, InvocationTargetException, InstantiationException, NotFoundException, NoSuchMethodException, ClassNotFoundException {
//        DriverGeneratorService driverGeneratorService = (DriverGeneratorService) ctx.getBean("driverGeneratorService");
//        AppiumDriver driver = driverGeneratorService.setLuCapabilities().getAppiumDriver();
//        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
//        int witdh = driver.manage().window().getSize().width;
//        int height = driver.manage().window().getSize().height;
//        for (int i = 0; i < 3; i++) {
//            driver.swipe(witdh * 9 / 10, height / 2, witdh / 10, height / 2, 1000);
//            Thread.sleep(3000);
//        }

        boolean result = true;
        Boolean br = (Boolean) result;
        System.out.println(br.booleanValue());

    }
}
