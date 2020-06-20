package com.example.demo.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.GPSService;
import com.example.demo.util.XMLUtil;

import io.jenetics.jpx.GPX;

@RestController
@RequestMapping("/gps")
public class GPSController {
	
	private final GPSService gpsService;
	
	public GPSController(GPSService gpsService) {
		this.gpsService = gpsService;
	}

	@RequestMapping(value = "/upload/{userId}", method = RequestMethod.POST)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile uploadFile, 
									@PathVariable("userId") Integer userId) throws IOException {
		
		// If file is empty return BAD REQUEST
		if (uploadFile.isEmpty()) {
            return new ResponseEntity<String>("Please select a file!", HttpStatus.BAD_REQUEST);
        }
		
		GPX gpx = XMLUtil.parseGPX(uploadFile.getBytes());
		gpsService.save(gpx, userId);
		
		return new ResponseEntity<String>("File uploaded successfully!", HttpStatus.OK);
	}
	
}
