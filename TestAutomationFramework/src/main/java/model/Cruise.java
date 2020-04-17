package model;

import java.util.ArrayList;
import java.util.List;

public class Cruise {

	List<CruiseItineraryDay> cruiseDays = new ArrayList<CruiseItineraryDay>();
	
	public void Cruise() {
	}
	
	
	public List<CruiseItineraryDay> getCruiseDays() {
		return cruiseDays;
	}

	public void setCruiseDays(List<CruiseItineraryDay> cruiseDays) {
		this.cruiseDays = cruiseDays;
	}
}
