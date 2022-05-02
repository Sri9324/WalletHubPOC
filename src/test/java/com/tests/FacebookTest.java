package com.tests;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.Facebook_Pages;
import com.utilities.ReadPropertiesFile;

public class FacebookTest extends Driver{
	public static final String filename = null;
	public Facebook_Pages facebook_page;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);

	@BeforeClass
	public void initialization() {
		Driver.init(prop.getProperty("Browser"));
		Facebook_Pages.navigateTo_LoginUI();
		Assert.assertEquals(Driver.driver.getTitle(), "Facebook – log in or sign up");
	}

	@Test(priority = 0)
	public void fb_StatusPostTest() {
		facebook_page = new Facebook_Pages();
		facebook_page.username_field(prop.getProperty("username_fb"));
		facebook_page.password_field(prop.getProperty("password_fb"));
		facebook_page.login();
		facebook_page.statusPostField();
		facebook_page.StatusMessageDraftTextArea("Hello World");
		facebook_page.post();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
