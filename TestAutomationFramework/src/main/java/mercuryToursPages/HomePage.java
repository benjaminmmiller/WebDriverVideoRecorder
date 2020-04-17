package mercuryToursPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseModel.MercuryToursPage;
import model.Flight;

public class HomePage extends MercuryToursPage{

	
	private static WebElement retrieveBodyTable(WebDriver wd){
		return retrieveParentTableRow(wd).findElement(By.xpath(".//td[2]/table/tbody/tr[4]/td/table"));
	}
	
	private static WebElement retrieveLeftSideContent(WebDriver wd) {
		return retrieveBodyTable(wd).findElement(By.xpath(".//tbody/tr/td[2]/table/tbody/tr[2]/td[1]"));
	}
	
	private static WebElement retrieveRightSideContent(WebDriver wd) {
		return retrieveBodyTable(wd).findElement(By.xpath(".//tbody/tr/td[2]/table/tbody/tr[2]/td[3]"));
	}
	
	private static WebElement retrieveUsernameField(WebDriver wd) {
		return retrieveRightSideContent(wd).findElement(By.xpath(".//form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input"));
	}
	
	private static WebElement retrievePasswordField(WebDriver wd) {
		return retrieveRightSideContent(wd).findElement(By.xpath(".//form/table/tbody/tr[4]/td/table/tbody/tr[3]/td[2]/input"));
	}
	
	private static WebElement retrieveDestinationsText(WebDriver wd) {
		return retrieveRightSideContent(wd).findElement(By.xpath(".//form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/font"));
	}
	
	public static WebElement retireveFlightsListTable(WebDriver wd) {
		return retrieveLeftSideContent(wd).findElement(By.xpath(".//table[1]/tbody/tr[3]/td/table"));
	}
	
	
	public static List<Flight> retrieveFlightSpecials(WebDriver wd){
		List<WebElement> flightRows = retireveFlightsListTable(wd).findElements(By.tagName("tr"));
		System.out.println("Number of Flights Found: "+flightRows.size());
		
		List<Flight> flights = new ArrayList<>();
		for(WebElement pageFlight:flightRows) {
			
			// Get the two main text objects from the row webelement
			String flightCities = pageFlight.findElement(By.xpath(".//td[1]/font")).getText();
			String flightCost = pageFlight.findElement(By.xpath(".//td[2]/div/font")).getText();
			
			System.out.println("Flight Cities: " + flightCities);
			System.out.println("Flight Cost: " + flightCost);
			
			//Separate the cities into origin and destination by splitting on the " to " text
			String[] cities = flightCities.split("\\sto\\s");
			String flightDepartingLocation = cities[0];
			String flightDestinationLocation = cities[1];
			
			System.out.println("Departing City: "+ flightDepartingLocation);
			System.out.println("Destination City :"+ flightDestinationLocation);
			
			// Create a new flight object and set all its values.
			Flight flight = new Flight();
			flight.setCost(Integer.parseInt(flightCost.replaceAll("[\\D]", "")));
			flight.setDepartingCity(flightDepartingLocation);
			flight.setDestinationCity(flightDestinationLocation);
			//Add the flight to the list of flights to be returned
			flights.add(flight);
		}
		
		return flights;
	}
	
	public static UnderConstructionPage clickDesintationLinkText(WebDriver wd) {
		retrieveDestinationsText(wd).findElement(By.tagName("a")).click();
		return new UnderConstructionPage();
	}
	
	public static void fillUsernameField(WebDriver wd, String value) {
		retrieveUsernameField(wd).sendKeys(value);
	}
	
	public static void fillPasswordField(WebDriver wd, String value) {
		retrievePasswordField(wd).sendKeys(value);
	}
}
