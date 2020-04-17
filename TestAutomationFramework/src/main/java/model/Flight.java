package model;

public class Flight {
	
	private String departingCity;
	private String destinationCity;
	private int cost;
	
	
	public String getDepartingCity() {
		return departingCity;
	}
	public void setDepartingCity(String departingCity) {
		this.departingCity = departingCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
}
