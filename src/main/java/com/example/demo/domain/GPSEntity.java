package com.example.demo.domain;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.jenetics.jpx.GPX;

@Entity
@Table(name = "gps")
public class GPSEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	
	@Column(name = "version")
	private String version;
	 
	@Column(name = "creator")
	private String creator;
	
	@Column(name = "ref_user_id")
	private Integer refUserId;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "gpx_file", columnDefinition="BLOB")
	private byte[] gpxFile;
	
	@OneToOne(mappedBy = "gps", cascade = CascadeType.ALL)
	private MetadataEntity metadata;
	
	@OneToMany(mappedBy = "gps", cascade = CascadeType.ALL)
	private List<WayPointEntity> wayPoints;
	
	@OneToMany(mappedBy = "gps", cascade = CascadeType.ALL)
	private List<TrackEntity> tracks;
	
	public GPSEntity() {}
	
	public GPSEntity(GPX gpx) {
		this.creator = gpx.getCreator();
		this.version = gpx.getVersion();
		this.setMetadata(gpx);
		this.setWayPoints(gpx);
		this.setTracks(gpx);
	}
	
	@PrePersist
	public void prePersist() {
		createdOn = new Date();
	}

	private void setTracks(GPX gpx) {
		if (gpx.getTracks() != null) {
			this.tracks = gpx.getTracks().stream().map(s -> new TrackEntity(s, this)).collect(Collectors.toList());
		}
	}

	private void setWayPoints(GPX gpx) {
		if (gpx.getWayPoints() != null) {
			this.wayPoints = gpx.getWayPoints().stream().map(s -> new WayPointEntity(s, this)).collect(Collectors.toList());
		}
	}

	private void setMetadata(GPX gpx) {
		if (gpx.getMetadata().isPresent()) {
			this.metadata = new MetadataEntity(gpx.getMetadata().get(), this);
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

	public MetadataEntity getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataEntity metadata) {
		this.metadata = metadata;
	}

	public List<WayPointEntity> getWayPoints() {
		return wayPoints;
	}

	public void setWayPoints(List<WayPointEntity> wayPoints) {
		this.wayPoints = wayPoints;
	}

	public List<TrackEntity> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackEntity> tracks) {
		this.tracks = tracks;
	}

	public Integer getRefUserId() {
		return refUserId;
	}

	public void setRefUserId(Integer refUserId) {
		this.refUserId = refUserId;
	}

	public int getId() {
		return id;
	}

	public byte[] getGpxFile() {
		return gpxFile;
	}

	public void setGpxFile(byte[] gpxFile) {
		this.gpxFile = gpxFile;
	}
	
}
