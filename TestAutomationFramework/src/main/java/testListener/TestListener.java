package testListener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.TestingFrameworkFileUtils;
import webDriverRegistry.WebdriverRegistry;

public class TestListener implements ITestListener{
	
	//Using Test Context
	/*@Override
	public void onTestFailure(ITestResult result) {
		ITestContext context = result.getTestContext();
		String wdID = result.getTestClass().getName()+result.getMethod().getMethodName();

		WebDriver wd = (WebDriver) context.getAttribute(wdID);	
		if(wd!=null) {
			TestingFrameworkFileUtils.takeWdScreenshot(wd);
		}
			
	}*/
	
	//Using Custom Registry
	public void onTestFailure(ITestResult result) {
		WebdriverRegistry webdriverRegistry = WebdriverRegistry.getInstance();
		String wdID = result.getTestClass().getName()+result.getMethod().getMethodName();

		System.out.println("Class name: "+result.getTestClass().getName());
		WebDriver wd = webdriverRegistry.getWebDriver(wdID);
		if(wd!=null) {
			TestingFrameworkFileUtils.takeWdScreenshot(wd);
		}
		
			
	}
}
