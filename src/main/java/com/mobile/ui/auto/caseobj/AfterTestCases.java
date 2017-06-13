package com.mobile.ui.auto.caseobj;

import org.springframework.stereotype.Component;

/**
 * Created by Jc on 16/9/11.
 */

@Component
public class AfterTestCases extends BaseTestCases{

    private String suiteName = "after-test-cases";

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }
}
