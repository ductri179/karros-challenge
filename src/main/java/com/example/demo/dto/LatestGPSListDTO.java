package com.example.demo.dto;

import java.util.List;

public class LatestGPSListDTO {
	
	private int page;
	private int size;
	private List<GPSDTO> data;
	
	public LatestGPSListDTO(int page, int size, List<GPSDTO> data) {
		super();
		this.page = page;
		this.size = size;
		this.data = data;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<GPSDTO> getData() {
		return data;
	}
	public void setData(List<GPSDTO> data) {
		this.data = data;
	}
	
}
