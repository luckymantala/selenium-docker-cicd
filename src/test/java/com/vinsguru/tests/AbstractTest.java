package com.vinsguru.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;

public abstract class AbstractTest {

    protected WebDriver driver;
	private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    
	@BeforeSuite
	public void setUpConfig() {
		Config.initialize();
	}
	
    @BeforeTest
    public void setDriver() throws MalformedURLException, URISyntaxException{
        this.driver =  Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        driver.manage().window().maximize();
    }
    
    public WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
    	
    	Capabilities capabilities = new ChromeOptions();
    	if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
    		capabilities = new FirefoxOptions();
    	}
    	String urlFormat = Config.get(Constants.GRID_URLFORMAT);
    	String hubHost = Config.get(Constants.GRID_HUBHOST);
    	String url = String.format(urlFormat, hubHost);
    	log.info("grid url: {}", url);
    	return new RemoteWebDriver(new URI(url).toURL(), capabilities);
    }
    
    public WebDriver getLocalDriver() {
    	if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
    		WebDriverManager.firefoxdriver().setup();
    		return new FirefoxDriver();
    	} else {
    		WebDriverManager.chromedriver().setup();
        	return new ChromeDriver();
    	}
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}
