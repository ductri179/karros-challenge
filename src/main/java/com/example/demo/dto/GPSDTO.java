package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.GPSEntity;
import com.example.demo.domain.UserEntity;

public class GPSDTO {

	private int id;
	private String version;
	private String creator;
	private UserDTO user;
	private MetadataDTO metadata;
	
	private List<WayPointDTO> wayPoints;
	
	private List<TrackDTO> tracks;
	
	public GPSDTO() {}
	
	public GPSDTO(GPSEntity gps, UserEntity user) {
		this.id = gps.getId();
		this.creator = gps.getCreator();
		this.version = gps.getVersion();
		this.user = user == null ? null : new UserDTO(user);
		this.setMetadata(gps);
		this.setWayPoints(gps);
		this.setTracks(gps);
	}

	private void setTracks(GPSEntity gps) {
		if (gps.getTracks() != null) {
			this.tracks = gps.getTracks().stream().map(s -> new TrackDTO(s)).collect(Collectors.toList());
		}
	}

	private void setWayPoints(GPSEntity gps) {
		if (gps.getWayPoints() != null) {
			this.wayPoints = gps.getWayPoints().stream().map(s -> new WayPointDTO(s)).collect(Collectors.toList());
		}
	}

	private void setMetadata(GPSEntity gps) {
		if (gps.getMetadata() != null) {
			this.metadata = new MetadataDTO(gps.getMetadata());
		}
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public MetadataDTO getMetadata() {
		return metadata;
	}

	public List<WayPointDTO> getWayPoints() {
		return wayPoints;
	}

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
