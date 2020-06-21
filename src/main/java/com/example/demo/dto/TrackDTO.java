package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.TrackEntity;

public class TrackDTO {

	private int id;
    private String name;
    private String description;
    private String source;
	private List<TrackSegmentDTO> trackSegments;
    
    public TrackDTO() {}

	public TrackDTO(TrackEntity track) {
		this.name = track.getName();
		this.description = track.getDescription();
		this.source = track.getSource();
		this.setSegments(track);
	}

	private void setSegments(TrackEntity track) {
		if (track.getTrackSegments() != null) {
			this.trackSegments = track.getTrackSegments().stream().map(s -> new TrackSegmentDTO(s)).collect(Collectors.toList());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<TrackSegmentDTO> getTrackSegments() {
		return trackSegments;
	}

	public void setTrackSegments(List<TrackSegmentDTO> trackSegments) {
		this.trackSegments = trackSegments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}
