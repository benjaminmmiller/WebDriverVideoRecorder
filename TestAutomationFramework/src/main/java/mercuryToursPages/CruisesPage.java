package mercuryToursPages;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseModel.MercuryToursPage;
import model.Cruise;
import model.CruiseItineraryDay;

public class CruisesPage extends MercuryToursPage{

	
	public WebElement retrieveCruiseContent(WebDriver wd) {
		return retrieveParentTableRow(wd).findElement(By.xpath(".//td[2]/table/tbody/tr[4]/td/table"));
	}
	
	
	public WebElement retrieveCruiseItinerary(WebDriver wd) {
		return retrieveCruiseContent(wd).findElement(By.xpath(".//tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/th/table"));
	} 
	
	public String retrieveCruiseDayText(WebDriver wd, int rowIndex) {
		return retrieveCruiseItinerary(wd).findElement(By.xpath(".//tbody/tr["+(rowIndex+4)+"]/td[1]")).getText();
	}
	
	public String retrieveCruiseDayPortText(WebDriver wd, int rowIndex) {
		return retrieveCruiseItinerary(wd).findElement(By.xpath(".//tbody/tr["+(rowIndex+4)+"]/td[2]")).getText();
	}
	
	public String retrieveCruiseDayArrivalTimeText(WebDriver wd, int rowIndex) {
		return retrieveCruiseItinerary(wd).findElement(By.xpath(".//tbody/tr["+(rowIndex+4)+"]/td[3]")).getText();
	}
	
	public String retrieveCruiseDayDepartureTimeText(WebDriver wd, int rowIndex) {
		return retrieveCruiseItinerary(wd).findElement(By.xpath(".//tbody/tr["+(rowIndex+4)+"]/td[4]")).getText();
	}
	
	
	public Cruise retrieveAllCruiseDaysForCruise(WebDriver wd){
		
		List<WebElement> cruiseDayRows = retrieveCruiseItinerary(wd).findElements(By.tagName("tr"));
		//Remove the first 3 rows. These contain the table headers/spacing and are redundant. 
		for(int i=0;i<3;i++) {
			cruiseDayRows.remove(0);
		}
		
		Cruise cruise = new Cruise();
		for(int i=0;i<cruiseDayRows.size();i++) {
			String cruiseDayRowText = retrieveCruiseDayText(wd,i);
			String cruiseDayPortText = retrieveCruiseDayPortText(wd,i);
			String cruiseDayArrivalTimeText = retrieveCruiseDayArrivalTimeText(wd,i);
			String cruiseDayDepartureTimeText = retrieveCruiseDayDepartureTimeText(wd, i);
			
			CruiseItineraryDay cruiseDay = new CruiseItineraryDay();
			cruiseDay.setDayOfWeek(cruiseDayRowText);
			cruiseDay.setPortOfCall(cruiseDayPortText);
			if(!cruiseDayDepartureTimeText.equals("---")&&!cruiseDayDepartureTimeText.equals("--")&&!cruiseDayDepartureTimeText.isBlank()) {
				if(cruiseDayDepartureTimeText.equals("Noon")) {
					cruiseDay.setDepartureDate("12:00 pm");
				}
				else {
					if(timeHasMinutes(cruiseDayDepartureTimeText)) {
						cruiseDay.setDepartureDate(cruiseDayDepartureTimeText);
					}
					else {
						cruiseDay.setDepartureDate(addMinutesToTime(cruiseDayDepartureTimeText).toUpperCase());
					}
				}
			}
			if(!cruiseDayArrivalTimeText.equals("---")&&!cruiseDayArrivalTimeText.equals("--")&&!cruiseDayArrivalTimeText.isBlank()) {
				if(cruiseDayArrivalTimeText.equals("Noon")) {
					cruiseDay.setArrivalDate("12:00 pm");
				}
				else {
					if(timeHasMinutes(cruiseDayArrivalTimeText)) {
						cruiseDay.setArrivalDate(cruiseDayArrivalTimeText);
					}
					else {
						cruiseDay.setArrivalDate(addMinutesToTime(cruiseDayArrivalTimeText).toUpperCase());
					}
					
				}
			}
			cruise.getCruiseDays().add(cruiseDay);
		}
		return cruise;
	}
	
	
	public boolean timeHasMinutes(String time) {
		String regexPattern = "(\\d+:\\d\\d\\s*am)|(\\d+:\\d\\d\\s*pm)";
		
		Pattern r = Pattern.compile(regexPattern);
		
		Matcher m = r.matcher(time);
		
		return m.find();
	}
	
	public String addMinutesToTime(String time)  {
		System.out.println("Adding minutes to: "+time);
		String[] splitValues = time.split("\\s+");
		if(splitValues.length!=2) {
			throw new IllegalArgumentException();
		}
		else {
			String hourValue = splitValues[0];
			String amPm = splitValues[1];
			hourValue+=":00";
			return hourValue+" "+amPm;
		}
		
	}
}
