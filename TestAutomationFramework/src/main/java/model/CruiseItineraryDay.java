package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CruiseItineraryDay {
	
	private String dayOfWeek;
	private Date arrivalDate;
	private Date departureDate;
	private String portOfCall;
	private SimpleDateFormat hourFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
	
	public void CruiseItinerary() {
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		try {
			this.arrivalDate = hourFormat.parse(arrivalDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate){
		try {
			this.departureDate = hourFormat.parse(departureDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPortOfCall() {
		return portOfCall;
	}

	public void setPortOfCall(String portOfCall) {
		this.portOfCall = portOfCall;
	}

}
