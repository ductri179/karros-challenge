package com.example.demo.dto;

import com.example.demo.domain.WayPointEntity;

public class WayPointDTO {

    private double latitude;
    private double longitude;
    private double elevation;
    private double speed;
    private String name;
    private String symbol;
    
    public WayPointDTO() {}

	public WayPointDTO(WayPointEntity wayPoint) {
		this.latitude = wayPoint.getLatitude();
		this.longitude = wayPoint.getLongitude();
		this.elevation = wayPoint.getElevation();
		this.speed = wayPoint.getSpeed();
		this.name = wayPoint.getName();
		this.symbol = wayPoint.getSymbol();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
    
}
