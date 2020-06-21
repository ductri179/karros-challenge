package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.jenetics.jpx.WayPoint;

@Entity
@Table(name = "waypoint")
public class WayPointEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
    
    @Column(name = "latitude")
    private double latitude;
    
    @Column(name = "longitude")
    private double longitude;
    
    
    @Column(name = "elevation")
    private double elevation;
    
    @Column(name = "speed")
    private double speed;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "symbol")
    private String symbol;
    
    @JoinColumn(name = "ref_gps_id", nullable = true)
    @ManyToOne
    private GPSEntity gps;
    
    @JoinColumn(name = "ref_track_segment_id", nullable = true)
    @ManyToOne
    private TrackSegmentEntity trackSegment;
    
    public WayPointEntity() {}

	public WayPointEntity(WayPoint wayPoint, GPSEntity gpsEntity) {
		this(wayPoint);
		this.gps = gpsEntity;
	}

	public WayPointEntity(WayPoint wayPoint, TrackSegmentEntity trackSegmentEntity) {
		this(wayPoint);
		this.trackSegment = trackSegmentEntity;
	}
	
	private WayPointEntity(WayPoint wayPoint) {
		this.latitude = wayPoint.getLatitude().doubleValue();
		this.longitude = wayPoint.getLongitude().doubleValue();
		this.elevation = wayPoint.getElevation().isPresent() ? wayPoint.getElevation().get().doubleValue() : 0;
		this.speed = wayPoint.getSpeed().isPresent() ? wayPoint.getSpeed().get().doubleValue() : 0;
		this.name = wayPoint.getName().isPresent() ? wayPoint.getName().get() : null;
		this.symbol = wayPoint.getSymbol().isPresent() ? wayPoint.getSymbol().get() : null;
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

	public TrackSegmentEntity getTrackSegment() {
		return trackSegment;
	}

	public void setTrackSegment(TrackSegmentEntity trackSegment) {
		this.trackSegment = trackSegment;
	}
    
}
