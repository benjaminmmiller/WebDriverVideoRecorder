package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseModel.CuLearnPage;

public class CoursePage extends CuLearnPage{

	public CoursePage(WebDriver wd, String pageTitle) {
		super(wd);
		//WebDriverWait wait = new WebDriverWait(wd, 50);
		//wait.until(ExpectedConditions.textToBePresentInElement(retrievePageHeader(wd), pageTitle));
		
	}

	
	public static List<WebElement> retrieveAllResources(WebDriver wd) {
		return wd.findElements(By.className("resource"));
	}
	
	public static WebElement retrievePageHeader(WebDriver wd) {
		return wd.findElement(By.id("page-header")).findElement(By.className("page-header-headings"));
	}
	
	public static void clickResource(WebDriver wd, WebElement resource) {
		WebElement linkToClick = resource.findElement(By.tagName("a"));
		linkToClick.sendKeys(Keys.CONTROL,Keys.RETURN);
	}
	
}
