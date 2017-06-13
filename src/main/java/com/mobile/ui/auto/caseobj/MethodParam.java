package com.mobile.ui.auto.caseobj;

import org.springframework.stereotype.Component;

/**
 * Created by Jc on 16/9/11.
 */

@Component
public class MethodParam extends BaseSuiteElementObject{

    private String name;
    private String type;
    private String value;

    public String getName() {
        return name;
    }

    public MethodParam setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public MethodParam setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public MethodParam setValue(String value) {
        this.value = value;
        return this;
    }
}
