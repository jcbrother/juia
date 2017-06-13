package com.mobile.ui.auto.caseobj;

import org.springframework.stereotype.Component;

/**
 * Created by Jc on 16/9/11.
 */

@Component
public class TestCases extends BaseTestCases{

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    private String suiteName = "test-cases";

}
