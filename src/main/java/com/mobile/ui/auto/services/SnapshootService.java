package com.mobile.ui.auto.services;

import com.mobile.ui.auto.controller.ExecutorEngine;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Jc on 16/8/15.
 */

@Service
public class SnapshootService {

    @Autowired
    public ExecutorEngine executorEngine;

    public File getSnapshootAsFile(){
        return executorEngine.getOprDriver().getScreenshotAs(OutputType.FILE);
    }


}
