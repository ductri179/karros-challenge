package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.example.demo.dto.GPSDTO;
import com.example.demo.dto.LatestGPSListDTO;
import com.example.demo.service.GPSService;
import com.example.demo.util.XMLUtil;

import io.jenetics.jpx.GPX;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GPSServiceTest {
	
	@Value("classpath:test.gpx")
	Resource xsdTest; 
	
	
	@Autowired
	GPSService gpsService;
	
	
	@Test
	public void saveGPXTest() throws IOException {
		File file = xsdTest.getFile();
		try (FileInputStream is = new FileInputStream(file)){
			byte[] b;
			b = new byte[is.available()];
			is.read(b);
			GPX gpx = XMLUtil.parseGPX(b);
			gpsService.save(gpx, 123, b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getLatestGPSTest() {
		LatestGPSListDTO list = gpsService.findLatest(0, 5);
		Assert.notNull(list, "List GPS is empty");
	}
	
	@Test
	public void getOne() {
		GPSDTO gps = gpsService.findGPSById(1);
		Assert.notNull(gps, "Cannot find GPS with id 1");
	}
	
	@Test
	public void getFile() {
		byte[] file = gpsService.findGPXFileById(1);
		Assert.notNull(file, "Cannot find GPS with id 1");
	}

}
