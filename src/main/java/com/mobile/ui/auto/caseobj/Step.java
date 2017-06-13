package com.mobile.ui.auto.caseobj;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by Jc on 16/9/11.
 */

@Component
public class Step extends BaseSuiteElementObject implements Comparable{

    private Integer id;
    private String srcPageName;
    private String method;
    private String destPageName;
    private boolean snapshoot;
    private String stepDesc;
    private String expectResult;
    private boolean stepResultPass = true;

    private LinkedList<MethodParam> methodParams;
    private LinkedList<AssertKey> assertKeys;

    public Integer getId() {
        return id;
    }

    public Step setId(int id) {
        this.id = id;
        return this;
    }

    public String getSrcPageName() {
        return srcPageName;
    }

    public Step setSrcPageName(String srcPageName) {
        this.srcPageName = srcPageName;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Step setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getDestPageName() {
        return destPageName;
    }

    public Step setDestPageName(String destPageName) {
        this.destPageName = destPageName;
        return this;
    }

    public boolean isSnapshoot() {
        return snapshoot;
    }

    public Step setSnapshoot(boolean snapshoot) {
        this.snapshoot = snapshoot;
        return this;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public Step setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
        return this;
    }

    public LinkedList<MethodParam> getMethodParams() {
        return methodParams;
    }

    public Step setMethodParams(LinkedList<MethodParam> methodParams) {
        this.methodParams = methodParams;
        return this;
    }

    public LinkedList<AssertKey> getAssertKeys() {
        return assertKeys;
    }

    public Step setAssertKeys(LinkedList<AssertKey> assertKeys) {
        this.assertKeys = assertKeys;
        return this;
    }

    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public boolean isStepResultPass() {
        return stepResultPass;
    }

    public void setStepResultPass(boolean stepResultPass) {
        this.stepResultPass = stepResultPass;
    }

    @Override
    public int compareTo(Object o) {
        Case aCase = (Case) o;
        int caseId = aCase.getId();
        return this.getId().compareTo(caseId);
    }
}
