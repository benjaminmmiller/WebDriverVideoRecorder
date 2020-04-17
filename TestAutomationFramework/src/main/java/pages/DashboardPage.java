package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseModel.CuLearnPage;
import tools.RetrievalTools;

public class DashboardPage extends CuLearnPage{

	public DashboardPage(WebDriver wd) {
		super(wd);
	}
	
	private WebElement retrieveSemesterExpandableSection(WebDriver wd) {
		return RetrievalTools.retrieveWebElementBeginsWithID(wd,"course_category_tree");
	}
	
	public List<WebElement> retrieveExpanableSemesters(WebDriver wd) {
		return RetrievalTools.retrieveWebElementByClassName(wd, "with_children");
	}

	public List<WebElement> retrieveSemesterClasses(WebDriver wd, WebElement semester){
		return semester.findElements(By.className("course"));
	}
	
	public CoursePage clickCourseButton(WebDriver wd, WebElement course) {
		System.out.println("Got here");
		String courseTitle = course.getText();
		course.click();
		return new CoursePage(wd, courseTitle);
	}
	
}
