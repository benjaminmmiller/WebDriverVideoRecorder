package baseModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mercuryToursPages.CruisesPage;
import mercuryToursPages.HomePage;
import mercuryToursPages.RegisterPage;
import mercuryToursPages.SignOnPage;
import mercuryToursPages.UnderConstructionPage;

public class MercuryToursPage extends Page{
	
	protected static WebElement retrieveParentTableBody(WebDriver wd) {
		return wd.findElement(By.xpath("/html/body/div/table/tbody"));
	}

	protected static WebElement retrieveParentTableRow(WebDriver wd) {
		return wd.findElement(By.xpath("/html/body/div/table/tbody/tr"));
	}
	
	protected static WebElement retireveTopNavigationBar(WebDriver wd) {
		return retrieveParentTableRow(wd).findElement(By.xpath(".//td[2]/table/tbody/tr[2]"));
	}
	
	protected static WebElement retrieveTopNavigationButton(WebDriver wd, int childIndex) {
		return retireveTopNavigationBar(wd).findElement(By.xpath(".//td/table/tbody/tr/td["+childIndex+"]/a"));
	}

	protected static WebElement retrieveSideMenu(WebDriver wd) {
		return retrieveParentTableRow(wd).findElement(By.xpath("//td[1]"));
	}
	
	protected static WebElement retireveSideNavigationBarTable(WebDriver wd) {
		return retrieveSideMenu(wd).findElement(By.xpath(".//table/tbody/tr/td/table/tbody/tr/td/table"));
	}
	
	protected static WebElement retrieveSideNavigationBarTableButton(WebDriver wd, int childIndex) {
		return retireveSideNavigationBarTable(wd).findElement(By.xpath(".//tbody/tr["+childIndex+"]/td[2]")).findElement(By.tagName("a"));
	}
	
	public HomePage clickSideNavBarHomeButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 1).click();
		return new HomePage();
	}
	
	public HomePage clickSideNavBarFlightsButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 2).click();
		return new HomePage();
	}
	
	public UnderConstructionPage clickSideNavBarHotelsButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 3).click();
		return new UnderConstructionPage();
	}
	
	public UnderConstructionPage clickSideNavBarCarRentalsButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 4).click();
		return new UnderConstructionPage();
	}
	
	public CruisesPage clickSideNavBarCruisesButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 5).click();
		return new CruisesPage();
	}
	
	public UnderConstructionPage clickSideNavBarDestinationsButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 6).click();
		return new UnderConstructionPage();
	}
	
	public UnderConstructionPage clickSideNavBarVacationsButton(WebDriver wd) {
		retrieveSideNavigationBarTableButton(wd, 7).click();
		return new UnderConstructionPage();
	}
	
	public SignOnPage clickTopNavBarSignOnButton(WebDriver wd) {
		retrieveTopNavigationButton(wd, 1).click();
		return new SignOnPage();
	}
	
	public RegisterPage clickTopNavBarRegisterButton(WebDriver wd) {
		retrieveTopNavigationButton(wd, 2).click();
		return new RegisterPage();
	}
	
	public UnderConstructionPage clickTopNavBarSupportButton(WebDriver wd) {
		retrieveTopNavigationButton(wd, 3).click();
		return new UnderConstructionPage();
	}
	
	public UnderConstructionPage clickTopNavBarContactButton(WebDriver wd) {
		retrieveTopNavigationButton(wd, 4).click();
		return new UnderConstructionPage();
	}
}
