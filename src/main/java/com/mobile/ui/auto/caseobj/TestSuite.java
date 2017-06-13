package com.mobile.ui.auto.caseobj;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by Jc on 16/9/11.
 */

@Component
public class TestSuite extends BaseSuiteElementObject{

    private String name;
    private String description;
    private LinkedList<Cases> casesList = null;

    public String getName() {
        return name;
    }

    public TestSuite setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TestSuite setDescription(String description) {
        this.description = description;
        return this;
    }

    public LinkedList<Cases> getCasesList() {
        return casesList;
    }

    public TestSuite setCasesList(LinkedList<Cases> casesList) {
        this.casesList = casesList;
        return this;
    }
}
