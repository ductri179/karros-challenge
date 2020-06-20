package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.util.XMLUtil;

import io.jenetics.jpx.GPX;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XMLUtilTest {
	
	@Value("classpath:test.gpx")
	Resource xsdTest; 
	
	
	@Test
	public void unmarshallTest() throws IOException {
		File file = xsdTest.getFile();
		try (FileInputStream is = new FileInputStream(file)){
			byte[] b;
			b = new byte[is.available()];
			is.read(b);
			GPX gpx = XMLUtil.parseGPX(b);
			
			Assert.assertNotNull("Failed to parse GPX", gpx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
