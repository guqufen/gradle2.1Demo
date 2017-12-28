package net.fnsco.core.constants;

import java.util.Map;

import com.beust.jcommander.internal.Maps;

public class CoreMapConstants {
	public  static Map<String,String> imageContentType = Maps.newHashMap();

	static {

	imageContentType.put("jpg","image/jpeg");

	imageContentType.put("jpeg","image/jpeg");

	imageContentType.put("png","image/png");

	imageContentType.put("tif","image/tiff");

	imageContentType.put("tiff","image/tiff");

	imageContentType.put("ico","image/x-icon");

	imageContentType.put("bmp","image/bmp");

	imageContentType.put("gif","image/gif");

	}
}	
