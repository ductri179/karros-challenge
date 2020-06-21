package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.TrackSegmentEntity;

public class TrackSegmentDTO {

	private List<WayPointDTO> wayPoints;
	private TrackDTO track;
	
	public TrackSegmentDTO() {}

	public TrackSegmentDTO(TrackSegmentEntity trackSegment) {
		if(trackSegment.getWayPoints() != null) {
			this.wayPoints = trackSegment.getWayPoints().stream().map(s -> new WayPointDTO(s)).collect(Collectors.toList());
		}
	}

	public List<WayPointDTO> getWayPoints() {
		return wayPoints;
	}

	public void setWayPoints(List<WayPointDTO> wayPoints) {
		this.wayPoints = wayPoints;
	}

	public TrackDTO getTrack() {
		return track;
	}

	public void setTrack(TrackDTO track) {
		this.track = track;
	}
	
}
