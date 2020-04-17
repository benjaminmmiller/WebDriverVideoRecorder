package tools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RetrievalTools {

	
	public static WebElement retrieveWebElementBeginsWithID(WebDriver wd, String beginsWithID) {
		return wd.findElement(By.cssSelector("[id^="+beginsWithID+"]"));
	}
	
	public static List<WebElement> retrieveWebElementByClassName(WebDriver wd, String className) {
		return wd.findElements(By.cssSelector("[class*="+className+"]"));
	}
}
