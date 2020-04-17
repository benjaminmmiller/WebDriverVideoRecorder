package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseModel.Page;

public class LoginPage extends Page{
	
	public LoginPage() {
		super();
	}

	private WebElement retrieveUserField(WebDriver wd) {
		return wd.findElement(By.id("user"));
	}

	private WebElement retrievePasswordField(WebDriver wd) {
		return wd.findElement(By.id("pass"));
	}
	
	private WebElement retrieveSubmitButton(WebDriver wd) {
		return wd.findElement(By.id("submit"));
	}
	
	public void fillUserField(WebDriver wd, String value) {
		WebElement userField = retrieveUserField(wd);
		userField.sendKeys(value);
	}
	
	public void fillPasswordField(WebDriver wd, String value) {
		WebElement passwordField = retrievePasswordField(wd);
		passwordField.sendKeys(value);
	}

	public DashboardPage login(String userID, String password, WebDriver wd) {
		fillUserField(wd, userID);
		fillPasswordField(wd, password);
		retrieveSubmitButton(wd).click();
		return new DashboardPage(wd);
	}
}
