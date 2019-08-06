package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.util.TestUtil;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;
	
	public TestBase() {
		 prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream("C:\\Users\\Md Ahmed\\eclipse-workspace\\POMFreeCRMTest\\"
					+ "src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);  // loading config file in properties object
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void initialization() {
		String browseName = prop.getProperty("browser"); // get the browser property and store in a string
		if(browseName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Md Ahmed\\Desktop\\chromedriver_win32\\chromedriver.exe");	
			driver = new ChromeDriver(); //launch chrome driver
		}else if (browseName.equals("FireFox")) {
		    	   System.setProperty("webdriver.gecko.driver","C:\\Users\\Md Ahmed\\Documents\\GreckoDriver\\geckodriver.exe");
			       driver = new FirefoxDriver();  
		       }
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		}
		
	}


