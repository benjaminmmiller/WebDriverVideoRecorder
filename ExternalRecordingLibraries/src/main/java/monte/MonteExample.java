package monte;

import java.io.IOException;

import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import mediaOutput.ScreenRecorderFactory;
import test.testScript;


/**
 * Example script for using the Monte Media Library screen recorder.
 * Default video output of the library is Users\(Windows User)\Videos\
 */
public class MonteExample {
	public static void main(String[] args) throws InterruptedException {
		WebDriver wd = new FirefoxDriver();
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		wd.manage().window().maximize();
		Thread.sleep(1000);
		ScreenRecorder screenRecorder = ScreenRecorderFactory.createNewScreenRecorder(1);
		try {
			screenRecorder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testScript.goThroughAllPages(wd);
		try {
			screenRecorder.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
