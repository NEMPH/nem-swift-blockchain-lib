package io.nem.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppPropertiesUtil {
	
	static Properties properties = new Properties();
	static {
		try {
			properties.load(AppPropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
