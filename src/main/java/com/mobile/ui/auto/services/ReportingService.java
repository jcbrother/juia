package com.mobile.ui.auto.services;

import com.mobile.ui.auto.caseobj.Case;

import com.mobile.ui.auto.components.PropertiesCenter;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jc on 17/1/8.
 */

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE) //每次容器获取新实例
public class ReportingService {

    @Autowired
    public PropertiesCenter propertiesCenter;

    public String caseTitleNodeStr = "<li id=\"$!{CASE_ID}\" class=\"$!{CASE_RESULT}\" onclick=\"showCaseDetails(this)\">[$!{CASE_ID}]$!{CASE_TITLE}</li>";
    public String caseDetailStepNodeStr = "<li>STEP$!{STEP_IDX}: $!{STEP_DETAIL}</li>";
    public String testResultStepNodeStr = "<li>STEP$!{STEP_IDX}>> Expect:  <span class=\"expect-result\">$!{EXPECT_RESULT}</span>   Actual:  <span class=\"$!{RESULT_STYLE_TYPE}\">$!{ACTUAL_RESULT}</span></li>";
    public String snapshootDataNodeStr = "<li id=\"$!{STEP_IDX}\" class=\"snapshoot-data\">$!{SNAPSHOOT_PATH}</li>";

    public LinkedList<String> caseTitleNodeStrList = new LinkedList<String>();
    public LinkedList<String> caseDetailStepNodeStrList = new LinkedList<String>();
    public LinkedList<String> testResultStepNodeStrList = new LinkedList<String>();
    public LinkedList<String> snapshootDataNodeStrList = new LinkedList<String>();

    public LinkedList<String> caseDetailDivsList = new LinkedList<String>();


    //初始化测试结果元素样式类型
    public Map<String,String> stepResultStyleType = new HashMap<String,String>(){
        {
            put("PASS","actual-result-pass");
            put("FAIL","actual-result-fail");
        }
    };

    public Map<String,String> caseResultStyleType = new HashMap<String,String>(){
        {
            put("PASS","case-pass");
            put("FAIL","case-fail");
        }
    };

    //添加用例标题
    public void addCaseTitle(String caseId, String caseTitle, String caseResult){
        caseTitleNodeStrList.add(caseTitleNodeStr.replace("$!{CASE_ID}", "case" + caseId)
                .replace("$!{CASE_RESULT}",caseResult)
                .replace("$!{CASE_TITLE}", caseTitle));
    }


    //添加用例步骤
    public void addDetailStep(String stepIdx, String stepDetail){
        caseDetailStepNodeStrList.add(caseDetailStepNodeStr.replace("$!{STEP_IDX}", stepIdx)
                .replace("$!{STEP_DETAIL}", stepDetail));
    }


    //添加每步测试结果
    public void addTestResultStep(String stepIdx, String expectResult, String resultStyleType, String actualResult){
        testResultStepNodeStrList.add(testResultStepNodeStr.replace("$!{STEP_IDX}", stepIdx)
                .replace("$!{EXPECT_RESULT}", expectResult)
                .replace("$!{RESULT_STYLE_TYPE}", resultStyleType)
                .replace("$!{ACTUAL_RESULT}", actualResult));
    }


    //添加截图标签
    public void addSnapshoot(String stepId, String snapshootPath){
        snapshootDataNodeStrList.add(snapshootDataNodeStr.replace("$!{STEP_IDX}", stepId)
                .replace("$!{SNAPSHOOT_PATH}", snapshootPath));
    }


    //合并兄弟元素标签
    public String buildMergedStrFromList(LinkedList<String> strList){
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = strList.iterator();
        while (it.hasNext()){
            sb.append(it.next());
            if(it.hasNext()){
                sb.append("\n\t\t");
            }
        }
        return sb.toString();
    }


    //构建case-details div标签
    public String buildCaseDetailsDiv(Case aCase) throws IOException, DocumentException {
        String detailsDiv = readTemplate("case-details-templates.xml");
        String firstPicPath = snapshootDataNodeStrList.size() == 0? "../../report-src/default-pic.png" : getFirstElement(snapshootDataNodeStrList).getText();
        detailsDiv = detailsDiv.replace("$!{CASE_ID}","case" + aCase.getId())
                .replace("$!{CASE_TITLE}", aCase.getTitle())
                .replace("$!{DETAIL_STEP_STR_LIST}", buildMergedStrFromList(caseDetailStepNodeStrList))
                .replace("$!{TEST_RESULT_STEP_STR_LIST}", buildMergedStrFromList(testResultStepNodeStrList))
                .replace("$!{FIRST_PIC_NAME}",Arrays.asList(firstPicPath.split(File.separator)).get(firstPicPath.split(File.separator).length-1))
                .replace("$!{FIRST_SNAPSHOOT_PIC}",firstPicPath)
                .replace("$!{FIRST_PIC_IDX}",snapshootDataNodeStrList.size() == 0 ? "0" :getFirstElement(snapshootDataNodeStrList).attribute("id").getValue())
                .replace("$!{SNAPSHOOT_LIST_LENGTH}",snapshootDataNodeStrList.size() == 0 ? "0" : String.valueOf(snapshootDataNodeStrList.size()))
                .replace("$!{SNAPSHOOT-LIST}",buildMergedStrFromList(snapshootDataNodeStrList));
        caseDetailDivsList.add(detailsDiv);
        caseDetailStepNodeStrList.clear();
        testResultStepNodeStrList.clear();
        snapshootDataNodeStrList.clear();
        return detailsDiv;
    }


    //合并所有case-details div标签
    public String buildAllCaseDetailsDivs(){
        return buildMergedStrFromList(caseDetailDivsList);
    }


    //合并所有case-title li标签
    public String buildAllCaseTitleLis() throws IOException {
        return readTemplate("case-titles-templates.xml").replace("$!{TITLE_NODE_STR_LIST}", buildMergedStrFromList(caseTitleNodeStrList));
    }


    //构建报告
    public String buildReport(String suiteName, Integer passNum, Integer failNum, Integer naNum ) throws IOException, DocumentException {
        String reportTemplate = readTemplate("JUAIReportTemplate.html");
        String reportStr = reportTemplate.replace("$!{APP_VERSION}",propertiesCenter.getRunConfigs().get("package.version"))
                .replace("$!{OS_VERSION}",propertiesCenter.getDeviceConfigs().get(propertiesCenter.getRunConfigs().get("mobile.device")).get("platformVersion").toString())
                .replace("$!{TESTER}", propertiesCenter.getRunConfigs().get("test.executor"))
                .replace("$!{SUITE_NAME}",suiteName)
                .replace("$!{TEST_ENV}",propertiesCenter.getRunConfigs().get("test.env"))
                .replace("$!{REPORT_TIME}",(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()))
                .replace("$!{ALL_CASE_LIST}",buildAllCaseTitleLis())
                .replace("$!{CASE_DETAIL_DIVS}",buildAllCaseDetailsDivs())
                .replace("$!{PASS_NUM}",passNum.toString())
                .replace("$!{FAIL_NUM}",failNum.toString())
                .replace("$!{NA_NUM}", naNum.toString())
                .replace("$!{FIRST_CASE_ID}",getFirstElement(caseTitleNodeStrList).attribute("id").getValue());
        return reportStr;
    }


    //生成报告到文件
    public void generateReport(String suiteName, Integer passNum, Integer failNum, Integer naNum, File snapshootsDir) throws IOException, DocumentException {
        String reportStr = buildReport(suiteName, passNum, failNum, naNum);
        String timeStamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        String reportDirStr = System.getProperty("user.dir") + File.separator + "JUIAReports" + File.separator + timeStamp;
        File dir = new File(reportDirStr);
        if(!dir.isDirectory()){
            dir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(new File(reportDirStr + File.separator + "JUIAReport.html" ));
        Writer os = new OutputStreamWriter(fos, "UTF-8");
        os.write(reportStr);
        os.flush();
        fos.close();
        snapshootsDir.renameTo(new File(reportDirStr + File.separator + "snapshoots")); //最后一步将截图目录移动到对应报告目录
    }


    //读取模板文档
    public String readTemplate(String tempName) throws IOException {
        String templateFilePath = this.getClass().getClassLoader().getResource("report-templates" + File.separator + tempName).getPath();
        FileUtils.readFileToString(new File(templateFilePath));
        return FileUtils.readFileToString(new File(templateFilePath));
    }


    //获取snapshoot-list第一个li元素
    public Element getFirstElement(LinkedList<String> aList) throws DocumentException {
        String firstElementStr = aList.getFirst();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new ByteArrayInputStream(firstElementStr.getBytes()));
        return doc.getRootElement();
    }


    //测试
    public static void main(String[] args) throws IOException, DocumentException {
        ReportingService rs = new ReportingService();
        System.out.println("hello");
        System.out.println(rs.getClass().getClassLoader().getResource("report-templates/case-titles-templates.xml").getPath());
        String s = rs.caseTitleNodeStr.replace("$!{CASE_ID}", "abc").replace("$!{CASE_TITLE}", "看直播");

        rs.addCaseTitle("case1", "我们的故事1","case-pass");
        rs.addCaseTitle("case2", "我们的故事2","case-fail");

        rs.addDetailStep("1", "告诉别人我们的故事1");
        rs.addDetailStep("2", "告诉别人我们的故事2");

        rs.addTestResultStep("1","没告诉我","actual-result-pass","pass");
        rs.addSnapshoot("1", "abc/sdh1");
        rs.addSnapshoot("2", "abc/sdh2");
        Case aCase = new Case();
        aCase.setId(1);
        aCase.setTitle("我们的故事");
        rs.buildCaseDetailsDiv(aCase);

    }
}
