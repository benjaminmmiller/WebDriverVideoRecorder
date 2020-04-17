package baseModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.DashboardPage;

public class CuLearnPage extends Page{
	
	public CuLearnPage(WebDriver wd) {
		super();
		WebDriverWait wait = new WebDriverWait(wd, 5);
		wait.until(ExpectedConditions.visibilityOf(retrieveNavBar(wd)));
	}
	
	
	
	private WebElement retrieveNavBar(WebDriver wd) {
		return wd.findElement(By.className("fixed-top"));
	}
	
	private WebElement retrieveToggleDrawerButton(WebDriver wd) {
		return retrieveNavBar(wd).findElement(By.xpath("//*[@id=\"page-wrapper\"]/nav/div/button"));
	}
	
	private boolean retrieveDrawerButtonState(WebDriver wd) {
		boolean toggleStatus = Boolean.parseBoolean(retrieveToggleDrawerButton(wd).getAttribute("aria-expanded"));
		return toggleStatus;
	}
	
	private WebElement retrieveNavDrawer(WebDriver wd) {
		return wd.findElement(By.id("nav-drawer"));
	}
	
	
	private WebElement retrieveDashboardButton(WebDriver wd) {
		return retrieveNavDrawer(wd).findElements(By.tagName("a")).get(0); 
	}
	
	public void clickDashboardButton(WebDriver wd) {
		retrieveDashboardButton(wd).click();
	}
	
	public void clickToggleDrawerButton(WebDriver wd) {
		retrieveToggleDrawerButton(wd).click();
	}
	
	public void enableNavDrawer(WebDriver wd) {
		boolean toggleStatus = retrieveDrawerButtonState(wd);
		if(toggleStatus==false) {
			clickToggleDrawerButton(wd);
		}
	}
	
	public DashboardPage returnToDashboard(WebDriver wd) {
		enableNavDrawer(wd);
		WebDriverWait wait = new WebDriverWait(wd, 5);
		wait.until(ExpectedConditions.visibilityOf(retrieveDashboardButton(wd)));
		clickDashboardButton(wd);
		return new DashboardPage(wd);
	}
	
	
	
}
