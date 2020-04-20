package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.testScript;
import utils.MiscUtils;
import videoRecorder.VideoManager;


/**
 * Test used for evaluating parallel testing. 
 * Contains two tests which run the same duplicate code. 
 * Called in ParallelTetst.xml. Test
 */
public class testNGVideoRecorder {
	@Test
	public void testNGTest1() {
		recordAndRunTest();
	}
	
	@Test
	public void testNGTest2() {
		recordAndRunTest();
	}
	
	private void recordAndRunTest() {
		VideoManager video = new VideoManager();
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().setSize(new Dimension(1920,1080));
		video.addWebDriver(wd);
		video.startRecordering();
		testScript.goThroughAllPages(wd);
		video.stopRecordering();
		video.packageVideo();
	}
}
