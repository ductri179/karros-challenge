package com.example.demo.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import io.jenetics.jpx.GPX;

/**
 * An utility class used to manipulate xml input.
 * 
 * @author tritran
 *
 */
public class XMLUtil {
	
	/**
	 * Parse given byte array to GPX object using JPX library.
	 * 
	 * @param bytes
	 * @return GPX object
	 * @throws IOException
	 */
	public static GPX parseGPX(byte[] bytes) throws IOException {
		return GPX.read(new ByteArrayInputStream(bytes));
	}

}
