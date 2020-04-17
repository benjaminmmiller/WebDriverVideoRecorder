package mercuryToursPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseModel.MercuryToursPage;

public class RegisterPage extends MercuryToursPage{

	
	public static WebElement retrieveContentTable(WebDriver wd) {
		return retrieveParentTableRow(wd).findElement(By.xpath(".//td[2]/table/tbody/tr[4]/td/table"));
	}
	
	public static WebElement retrieveFormTable(WebDriver wd) {
		return retrieveContentTable(wd).findElement(By.xpath(".//tbody/tr/td[2]/table/tbody/tr[5]/td/form/table"));
	}
	
	public static WebElement retrieveFieldFromTableBasedOnRowIndex(WebDriver wd, int index) {
		return retrieveFormTable(wd).findElement(By.xpath(".//tbody/tr["+index+"]/td[2]/input"));
	}
	
	public void fillFirstnameField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 2).sendKeys(value);
	}
	
	public void fillLastnameField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 3).sendKeys(value);
	}
	
	public void fillPhoneField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 4).sendKeys(value);
	}
	
	public void fillEmailField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 5).sendKeys(value);
	}
	
	public void fillFirstAddressField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 7).sendKeys(value);
	}
	
	public void fillSecondAddressField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 8).sendKeys(value);
	}
	
	public void fillCityField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 9).sendKeys(value);
	}
	
	public void fillStateField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 10).sendKeys(value);
	}
	
	public void fillPostalCodeField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 11).sendKeys(value);
	}
	
	public void fillCountryDropdown(WebDriver wd, String value) {
		retrieveFormTable(wd).findElement(By.xpath(".//tbody/tr[12]/td[2]/select")).sendKeys(value);
	}
	
	public void fillUsernameField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 14).sendKeys(value);
	}
	
	public void fillPasswordField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 15).sendKeys(value);
	}
	
	public void fillConfirmPasswordField(WebDriver wd, String value) {
		retrieveFieldFromTableBasedOnRowIndex(wd, 16).sendKeys(value);
	}
}
