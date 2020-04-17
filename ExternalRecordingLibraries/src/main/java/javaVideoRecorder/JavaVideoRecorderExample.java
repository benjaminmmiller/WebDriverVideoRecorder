package javaVideoRecorder;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;

import test.testScript;


/**
 * Example of using Java Video Recorder library (https://github.com/SergeyPirogov/video-recorder-java)
 * Video is outputed to \Videos folder created in project root directory 
 * Properties can be set in video.properties file found under src/main/resources
 */
@Listeners(UniversalVideoListener.class)
public class JavaVideoRecorderExample {
	@Test
	@Video (name="Java Video Recorder")
	public void javaVideoRecorder() {
		WebDriver wd = new FirefoxDriver();
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		wd.manage().window().setSize(new Dimension(1920,1080));
		testScript.goThroughAllPages(wd);
	}
}
