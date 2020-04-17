package tests;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.MiscUtils;
import mercuryToursPages.CruisesPage;
import mercuryToursPages.HomePage;
import mercuryToursPages.RegisterPage;
import mercuryToursPages.SignOnPage;
import mercuryToursPages.UnderConstructionPage;
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
		goThroughAllPages(wd);
	
		video.stopRecordering();
		video.packageVideo();
	}
	
	public static void goThroughAllPages(WebDriver wd) throws InterruptedException {
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		HomePage homePage = new HomePage();
		MiscUtils.delay(1000L);
		SignOnPage signon = homePage.clickTopNavBarSignOnButton(wd);
		MiscUtils.delay(1000L);
		RegisterPage registerPage =  signon.clickTopNavBarRegisterButton(wd);
		//First set of fields
		registerPage.fillFirstnameField(wd, "John");
		registerPage.fillLastnameField(wd, "Smith");
		registerPage.fillPhoneField(wd, "987-654-3210");
		registerPage.fillEmailField(wd, "fakeEmailAddress@ThisIsAFakeEmailAddress.ca");
		
		//Second set of fields
		registerPage.fillFirstAddressField(wd, "Address 1");
		registerPage.fillSecondAddressField(wd, "Address 2");
		registerPage.fillCityField(wd, "Toronto");
		registerPage.fillStateField(wd, "Ontario");
		registerPage.fillPostalCodeField(wd, "B7Y-9K2");
		registerPage.fillCountryDropdown(wd, "CANADA");
		
		//Third set of fields (user credentials)
		registerPage.fillUsernameField(wd, "Fake Username");
		registerPage.fillPasswordField(wd, "FakePassword123$%^");
		registerPage.fillConfirmPasswordField(wd, "FakePassword123$%^");
		
		MiscUtils.delay(1000L);
		UnderConstructionPage supportPage = registerPage.clickTopNavBarSupportButton(wd);
		MiscUtils.delay(1000L);
		UnderConstructionPage contactPage = supportPage.clickTopNavBarSupportButton(wd);
		
		MiscUtils.delay(1000L);
		homePage = contactPage.clickSideNavBarFlightsButton(wd);
		MiscUtils.delay(1000L);
		UnderConstructionPage hotels = homePage.clickSideNavBarCarRentalsButton(wd);
		MiscUtils.delay(1000L);
		UnderConstructionPage carPage = hotels.clickSideNavBarCarRentalsButton(wd);
		
		MiscUtils.delay(1000L);
		CruisesPage cruises = carPage.clickSideNavBarCruisesButton(wd);
		MiscUtils.delay(1000L);
		UnderConstructionPage destinationsPage = cruises.clickSideNavBarDestinationsButton(wd);
		MiscUtils.delay(1000L);
		UnderConstructionPage vacationsPage = destinationsPage.clickSideNavBarVacationsButton(wd);
		MiscUtils.delay(1000L);
	}
}
