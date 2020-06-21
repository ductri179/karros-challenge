package com.example.demo.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.jenetics.jpx.TrackSegment;

@Entity
@Table(name = "track_segment")
public class TrackSegmentEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
    
	@OneToMany(mappedBy = "trackSegment", cascade = CascadeType.ALL)
	private List<WayPointEntity> wayPoints;
	
	@JoinColumn(name = "ref_track_id")
	@ManyToOne
	private TrackEntity track;
	
	public TrackSegmentEntity() {}

	public TrackSegmentEntity(TrackSegment trackSegment, TrackEntity trackEntity) {
		this.track = trackEntity;
		this.setWayPoints(trackSegment);
	}

	private void setWayPoints(TrackSegment trackSegment) {
		if(trackSegment.getPoints() != null) {
			this.wayPoints = trackSegment.getPoints().stream().map(s -> new WayPointEntity(s, this)).collect(Collectors.toList());
		}
	}

	public List<WayPointEntity> getWayPoints() {
		return wayPoints;
	}

	public void setWayPoints(List<WayPointEntity> wayPoints) {
		this.wayPoints = wayPoints;
	}

	public TrackEntity getTrack() {
		return track;
	}

	public void setTrack(TrackEntity track) {
		this.track = track;
	}
	
}
