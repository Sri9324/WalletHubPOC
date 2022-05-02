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
import com.pages.Home_Pages;
import com.utilities.ReadPropertiesFile;

public class ReviewsTest extends Driver{
	public static final String filename = null;
	public Home_Pages home_page;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);

	@BeforeClass
	public void initialization() {
		Driver.init(prop.getProperty("Browser"));
		Home_Pages.navigateTo_homeUI();
		Assert.assertEquals(Driver.driver.getTitle(), "test insurance company metatitle test");
	}

	@Test(priority = 0)
	public void ratingReview_Test() {
		home_page = new Home_Pages();
		home_page.reviews();
		home_page.ratingStar();
		home_page.select_HealthInsurance();
		home_page.select_writeRiview(prop.getProperty("Text"));
		home_page.submitReview();
		home_page.loginClick();
		home_page.username_field(prop.getProperty("username"));
		home_page.password_field(prop.getProperty("password"));
		home_page.login();
		String actualText = home_page.getReviewText();
		Assert.assertEquals(actualText, "Your Review");
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
