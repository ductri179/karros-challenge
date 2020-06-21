package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.jenetics.jpx.Metadata;

@Entity
@Table(name = "metadata")
public class MetadataEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description", length = 2048)
    private String description;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "time")
    private Date time;
    
    @JoinColumn(name = "ref_gps_id")
    @OneToOne
    private GPSEntity gps;
    
    public MetadataEntity() {}

	public MetadataEntity(Metadata metadata, GPSEntity gpsEntity) {
		this.name = metadata.getName().isPresent() ? metadata.getName().get() : null;
		this.description = metadata.getDescription().isPresent() ? metadata.getDescription().get() : null;
		this.author = metadata.getAuthor().isPresent() ? 
							(
									metadata.getAuthor().get().getName().isPresent() ? 
									metadata.getAuthor().get().getName().get() 
									: null 
							)
							: null;
		this.time = metadata.getTime().isPresent() ? Date.from(metadata.getTime().get().toInstant()) : null;
		this.gps = gpsEntity;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public GPSEntity getGps() {
		return gps;
	}

	public void setGps(GPSEntity gps) {
		this.gps = gps;
	}
    
}
