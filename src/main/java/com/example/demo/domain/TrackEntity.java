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

import io.jenetics.jpx.Track;

@Entity
@Table(name = "track")
public class TrackEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "source")
    private String source;
    
    @JoinColumn(name = "ref_gps_id")
    @ManyToOne
    private GPSEntity gps;
    
    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
	private List<TrackSegmentEntity> trackSegments;

	public TrackEntity(Track track, GPSEntity gpsEntity) {
		this.name = track.getName().isPresent() ? track.getName().get() : null;
		this.description = track.getDescription().isPresent() ? track.getDescription().get() : null;
		this.source = track.getSource().isPresent() ? track.getSource().get() : null;
		this.gps = gpsEntity;
		this.setSegments(track);
	}

	private void setSegments(Track track) {
		if (track.getSegments() != null) {
			this.trackSegments = track.getSegments().stream().map(s -> new TrackSegmentEntity(s, this)).collect(Collectors.toList());
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

	public GPSEntity getGps() {
		return gps;
	}

	public void setGps(GPSEntity gps) {
		this.gps = gps;
	}

	public List<TrackSegmentEntity> getTrackSegments() {
		return trackSegments;
	}

	public void setTrackSegments(List<TrackSegmentEntity> trackSegments) {
		this.trackSegments = trackSegments;
	}
    
}
