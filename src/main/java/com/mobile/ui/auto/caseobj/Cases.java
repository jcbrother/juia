package com.mobile.ui.auto.caseobj;

/**
 * Created by jiangcan on 16/9/12.
 */
public class Cases extends BaseSuiteElementObject{

    private boolean isExecBeforeTest;
    private boolean isExecAfterTest;
    private BeforeTestCases beforeTestCases;
    private TestCases testCases;
    private AfterTestCases afterTestCases;

    public boolean isExecBeforeTest() {
        return isExecBeforeTest;
    }

    public Cases setIsExecBeforeTest(boolean isExecBeforeTest) {
        this.isExecBeforeTest = isExecBeforeTest;
        return this;
    }

    public boolean isExecAfterTest() {
        return isExecAfterTest;
    }

    public Cases setIsExecAfterTest(boolean isExecAfterTest) {
        this.isExecAfterTest = isExecAfterTest;
        return this;
    }

    public BeforeTestCases getBeforeTestCases() {
        return beforeTestCases;
    }

    public Cases setBeforeTestCases(BeforeTestCases beforeTestCases) {
        this.beforeTestCases = beforeTestCases;
        return this;
    }

    public TestCases getTestCases() {
        return testCases;
    }

    public Cases setTestCases(TestCases testCases) {
        this.testCases = testCases;
        return this;
    }

    public AfterTestCases getAfterTestCases() {
        return afterTestCases;
    }

    public Cases setAfterTestCases(AfterTestCases afterTestCases) {
        this.afterTestCases = afterTestCases;
        return this;
    }
}

