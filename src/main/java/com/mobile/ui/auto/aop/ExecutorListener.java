package com.mobile.ui.auto.aop;

import com.mobile.ui.auto.assertions.AssertUtils;
import com.mobile.ui.auto.caseobj.*;
import com.mobile.ui.auto.controller.ExecutorEngine;
import com.mobile.ui.auto.services.ReportingService;
import com.mobile.ui.auto.services.SnapshootService;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Jc on 16/10/15.
 */

@Aspect
@Component
public class ExecutorListener {

    @Autowired
    public AssertUtils assertUtils;
    @Autowired
    public ReportingService reportingService;
    @Autowired
    public SnapshootService snapshootService;
    @Autowired
    public ExecutorEngine executorEngine;

    public File snapshootsDir = new File(System.getProperty("user.dir") + File.separator + "snapshoots");


    @Pointcut("@annotation(com.mobile.ui.auto.anotations.SuiteExecutor)")
    public void executeSuite(){}

    @Pointcut("@annotation(com.mobile.ui.auto.anotations.CaseExecutor)")
    public void executeCase(){}

    @Pointcut("@annotation(com.mobile.ui.auto.anotations.StepExecutor)")
    public void executeStep(){}

    @Pointcut("@annotation(com.mobile.ui.auto.anotations.UIAssertion)")
    public void onFailureOpr(){}


    @Around("executeStep()")
    public Step stepExecutionListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //aCase用于截图命名
        Case aCase = (Case) proceedingJoinPoint.getArgs()[0];
        Step result = (Step) proceedingJoinPoint.proceed();
        LinkedList<AssertKey> assertKeys = result.getAssertKeys();
        boolean assertResult = true;
        for(AssertKey assertKey : assertKeys){
            assertResult = assertUtils.assertKey(assertKey);
            if(assertResult == false){
                result.setStepResultPass(false);
                break;
            }
        }
        reportingService.addDetailStep(String.valueOf(result.getId()), result.getStepDesc());
        String resultStyleType = assertResult ? reportingService.stepResultStyleType.get("PASS"):reportingService.stepResultStyleType.get("FAIL");
        reportingService.addTestResultStep(String.valueOf(result.getId()), result.getExpectResult(),resultStyleType,String.valueOf(assertResult));
        //截图操作
        if(result.isSnapshoot() || !result.isStepResultPass()){
            //如果用例步骤设置了截图或者断言失败，截图并保存至该工程临时目录snapshoots
            File snapshoot = snapshootService.getSnapshootAsFile();
            String snapshootName = "case" + aCase.getId() + "-step" + result.getId() +"-snapshoot.jpg";
            String snapshootPath = snapshootsDir.getAbsolutePath() + File.separator + snapshootName;
            FileUtils.copyFile(snapshoot,new File(snapshootPath));
            reportingService.addSnapshoot(result.getId().toString(),"snapshoots" + File.separator + snapshootName);
        }
        return result;
    }


    @Around("executeCase()")
    public Boolean caseExecutionListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Case aCase = (Case) args[0];
        boolean assertResult = true;
        Boolean result = (Boolean) proceedingJoinPoint.proceed();
        String resultStyleType = assertResult ? reportingService.caseResultStyleType.get("PASS"):reportingService.caseResultStyleType.get("FAIL");
        reportingService.addCaseTitle(String.valueOf(aCase.getId()),aCase.getTitle(), resultStyleType);
        reportingService.buildCaseDetailsDiv(aCase);
        return result;
    }

    @After("executeSuite()")
    public void afterSuiteExecutionListener(JoinPoint joinPoint) throws IOException, DocumentException {
        //生成报告
        Integer countPass = 0;
        Integer countFail = 0;
        LinkedList<Cases> casesList = executorEngine.getTestSuite().getCasesList();
        for(Cases cases:casesList){
            countPass += cases.getBeforeTestCases().countPass() + cases.getTestCases().countPass() + cases.getAfterTestCases().countPass();
            countFail += cases.getBeforeTestCases().countFail() + cases.getTestCases().countFail() + cases.getAfterTestCases().countFail();
        }
        reportingService.generateReport(executorEngine.getTestSuite().getName(), countPass, countFail, 0, snapshootsDir);
        System.out.println("Report Generated.");
    }

    @Before("executeSuite()")
    public void beforeSuiteExecutionListener(JoinPoint joinPoint){
        Object suiteObj = joinPoint.getArgs()[0];
        //执行正式用例前清空snapshoots目录
        if(suiteObj instanceof TestCases){
            emptyDir(snapshootsDir);//清空snapshoots目录
        }
    }

    public void emptyDir(File dir){
        if(!dir.isDirectory()){
            dir.mkdirs();
        }else{
            File[] files = dir.listFiles();
            for (int i=0;i<files.length;i++){
                deleteFile(files[i]);
            }
        }
    }

    public void deleteFile(File file){
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0;i < files.length;i ++) {
                    this.deleteFile(files[i]);
                }
                file.delete();
            }
        } else {
            System.out.println("file does not exist!");
        }
    }

}
