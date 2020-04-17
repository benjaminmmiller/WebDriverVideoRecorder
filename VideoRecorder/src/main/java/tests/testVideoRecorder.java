package tests;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.MiscUtils;
import mercuryToursPages.CruisesPage;
import mercuryToursPages.HomePage;
import mercuryToursPages.RegisterPage;
import mercuryToursPages.SignOnPage;
import mercuryToursPages.UnderConstructionPage;
import test.testScript;
import videoRecorder.VideoManager;
import videoRecorder.VideoRecorder.RecordType;

/**
 * Class used to test the VideoRecorder
 */
public class testVideoRecorder {
	
	/**
	 * Creates a WebDriver instance and starts recording, runs a test, stop recording, and then packages the video.
	 */
	public static void main(String[] args) throws InterruptedException {
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().setSize(new Dimension(1920,1080));
	
		VideoManager video = new VideoManager();
		video.addWebDriver(wd, 30, RecordType.BASE64);

		video.startRecordering();
		testScript.goThroughAllPages(wd);
	
		video.stopRecordering();
		video.packageVideo();
	}
	
	
}
