package com.mobile.ui.auto.assertions;

import com.mobile.ui.auto.anotations.UIAssertion;
import com.mobile.ui.auto.caseobj.AssertKey;
import com.mobile.ui.auto.components.LocatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jc on 17/1/23.
 */

@Component
public class AssertUtils {

    @Autowired
    public LocatorUtils locatorUtils;


    //断言相等
    @UIAssertion(description = "equals")
    public boolean assertEquals(String expect, String actual){
        return expect.equals(actual) ? true : false;
    }

    //断言不相等
    @UIAssertion(description = "not-equals")
    public boolean assertNotEquals(String expect, String actual){
        return expect.equals(actual) ? false : true;
    }

    //断言可见性
    @UIAssertion(description = "visibilty")
    public boolean assertVisibility(String locationType, String locator){
        return locatorUtils.locateElement(locationType,locator) != null ? true : false;
    }

    //断言不可见性
    @UIAssertion(description = "invisibilty")
    public boolean assertInvisibility(String locationType, String locator){
        return locatorUtils.locateElement(locationType,locator) == null ? true : false;
    }


    public boolean assertKey(AssertKey key){
        String locationType = key.getLocationType();
        String locator = key.getValue();
        String expectedValue = key.getCompareValue();
        return assertEquals(locatorUtils.locateElement(locationType,locator).getText(),expectedValue);

    }


}
