package com.example.demo.dto;

import java.util.Date;

import com.example.demo.domain.MetadataEntity;

public class MetadataDTO {

    private String name;
    private String description;
    private String author;
    private Date time;
    private GPSDTO gps;
    
    public MetadataDTO() {}

	public MetadataDTO(MetadataEntity metadata) {
		this.name = metadata.getName();
		this.description = metadata.getDescription();
		this.author = metadata.getAuthor();
		this.time = metadata.getTime();
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

	public GPSDTO getGps() {
		return gps;
	}

	public void setGps(GPSDTO gps) {
		this.gps = gps;
	}
    
}
