package com.example.demo.service;

import com.example.demo.dto.GPSDTO;
import com.example.demo.dto.LatestGPSListDTO;

import io.jenetics.jpx.GPX;

public interface GPSService {

	public void save(GPX gpx, int userId);
	public LatestGPSListDTO findLatest(int page, int size);
	public GPSDTO findGPSById(Integer id);

}
