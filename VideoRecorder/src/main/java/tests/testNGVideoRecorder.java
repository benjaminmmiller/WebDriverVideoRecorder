package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.MiscUtils;
import videoRecorder.VideoManager;

public class testNGVideoRecorder {

	
	
	@Test
	public void testNGTest1() {
		VideoManager video = new VideoManager();
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().setSize(new Dimension(1920,1080));
		video.addWebDriver(wd);
		video.startRecordering();
		try {
			testVideoRecorder.goThroughAllPages(wd);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MiscUtils.delay(600000L);
		video.stopRecordering();
		video.packageVideo();
	}
	
	
	
	
	/*@Test
	public void testNGTest2() {
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().setSize(new Dimension(1920,1080));
		System.out.println(video);
	}*/
}
