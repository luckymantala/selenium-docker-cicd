package com.vinsguru.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	
	private static final Logger log = LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "config/default.properties";
	private static Properties properties;
		
	public static void initialize() {
		
//		load existing default properties
		properties = loadProperties();
		
//		check if properties are override
		for(String key: properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}
			
//		test properties
		log.info("---------------------");
		for(String key: properties.stringPropertyNames()) {
			log.info("{}={}", key, properties.getProperty(key));
		}
		log.info("---------------------");
		
		
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
	public static Properties loadProperties() {
		System.out.println(DEFAULT_PROPERTIES);
		Properties properties = new Properties();
		try(InputStream inputStream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
			properties.load(inputStream);
		} catch (Exception e) {
			log.error("error loading properties file {}.", DEFAULT_PROPERTIES, e);
		}
		return properties;
	}
}
