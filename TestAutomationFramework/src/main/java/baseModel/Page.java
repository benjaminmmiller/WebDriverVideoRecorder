package baseModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
	private static String url;
	private static String pageTitle;
	
	public Page() {
		//pageTitle = wd.findElement(By.tagName("title")).getText();
	}
	
	public void goToPage(WebDriver wd, String url) {
		wd.get(url);
	}
	
	public String retrievePageTitle(WebDriver wd) {
		return wd.getTitle();
	}
}
