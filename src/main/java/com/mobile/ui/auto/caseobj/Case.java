package com.mobile.ui.auto.caseobj;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by Jc on 16/9/11.
 */

@Component
public class Case extends BaseSuiteElementObject implements Comparable{

    private Integer id;
    private String title;
    private String priority;
    private LinkedList<Step> steps = new LinkedList<Step>();
    private Boolean caseResultPass = null;

    public Integer getId() {
        return id;
    }

    public Case setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Case setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public Case setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public LinkedList<Step> getSteps(){
        return steps;
    }

    public Case setSteps(LinkedList<Step> steps) {
        this.steps = steps;
        return this;
    }

    public Boolean isCaseResultPass() {
        return caseResultPass;
    }

    public void setCaseResultPass(boolean caseResultPass) {
        this.caseResultPass = caseResultPass;
    }

    public String getAllStepDesc(){
        StringBuffer stringBuffer = new StringBuffer();
        String stepDesc;
        for(Step step:steps){
            if(steps.indexOf(step)!=(steps.size()-1)){
                stepDesc = step.getStepDesc() + "\n";
            }else{
                stepDesc = step.getStepDesc();
            }
            stringBuffer.append(stepDesc);
        }
        return stringBuffer.toString();
    }

    @Override
    public int compareTo(Object o) {
        Case aCase = (Case) o;
        int caseId = aCase.getId();
        return this.getId().compareTo(caseId);
    }
}
