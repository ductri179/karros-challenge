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

import com.example.demo.dto.GPSDTO;
import com.example.demo.dto.LatestGPSListDTO;
import com.example.demo.service.GPSService;
import com.example.demo.util.XMLUtil;

import io.jenetics.jpx.GPX;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/gps")
@Api(value = "GPSController", description = "Get latest GPSs, upload gpx file, get detail of GPS by ID")
public class GPSController {
	
	private final GPSService gpsService;
	
	public GPSController(GPSService gpsService) {
		this.gpsService = gpsService;
	}

	@RequestMapping(value = "/upload/{userId}", method = RequestMethod.POST)
	@ApiOperation(value = "Upload GPX file", response = String.class)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile uploadFile, 
									@PathVariable("userId") Integer userId) throws IOException {
		
		// If file is empty return BAD REQUEST
		if (uploadFile.isEmpty()) {
            return new ResponseEntity<String>("Please select a file!", HttpStatus.BAD_REQUEST);
        }
		
		GPX gpx = XMLUtil.parseGPX(uploadFile.getBytes());
		gpsService.save(gpx, userId, uploadFile.getBytes());
		
		return new ResponseEntity<String>("File uploaded successfully!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	@ApiOperation(value = "Get latest GPSs list, input page and size", response = LatestGPSListDTO.class)
	public ResponseEntity<?> findLatest(@RequestParam("page") Integer page, 
										@RequestParam("size") Integer size) {
		
		LatestGPSListDTO latest = gpsService.findLatest(page, size);
		
		return new ResponseEntity<LatestGPSListDTO>(latest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get info of GPS by given ID", response = GPSDTO.class)
	public ResponseEntity<?> get(@PathVariable("id") Integer id) {
		
		GPSDTO gps = gpsService.findGPSById(id);
		if (gps == null) {
			return new ResponseEntity<String>("GPS not found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<GPSDTO>(gps, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get GPX File by id")
	public ResponseEntity<?> download(@PathVariable("id") Integer id) {
		
		byte[] file = gpsService.findGPXFileById(id);
		
		if (file == null) {
			return new ResponseEntity<String>("GPS not found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<byte[]>(file, HttpStatus.OK);
	}
	
}
