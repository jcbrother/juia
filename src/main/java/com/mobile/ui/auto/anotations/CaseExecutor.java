package com.mobile.ui.auto.anotations;

import java.lang.annotation.*;

/**
 * Created by Jc on 17/1/16.
 * 自定义注解，用于拦截用例执行方法
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaseExecutor {

    String description() default "UIA Case Executor";

}
