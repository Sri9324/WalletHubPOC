package com.pages;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class Home_Pages extends Driver{
	public static final String filename = null;
	public static ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public static Properties prop = readPropertiesFile.readPropertiesFile(filename);

	@FindBy(xpath = "(//span[contains(text(),'Reviews')])[3]")
	WebElement reviews_link;
	
	@FindBy(xpath = "(//h3[contains(text(),'What')]/../review-star/div//*[name()='svg'])[4]")
	WebElement rating_star;
	
	@FindBy(xpath = "//span[contains(text(),'Select')]")
	WebElement select_dropdown;
	
	@FindBy(xpath = "//span[contains(text(),'Select')]/../ul/li[2]")
	WebElement select_dropdown_value;
	
	@FindBy(xpath = "//textarea[@placeholder='Write your review...']")
	WebElement write_review_textarea;
	
	@FindBy(xpath = "//div[contains(text(),'Submit')]")
	WebElement submit_button;
	
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement login_link;
	
	@FindBy(xpath = "//input[@placeholder='Email Address']")
	WebElement username;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath = "//*[@id='join-light']/form/div/div[3]/button")
	WebElement login_button;
	
	@FindBy(xpath = "//span[contains(text(),'Your Review')]")
	WebElement your_review_text;
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	public Home_Pages() {
		PageFactory.initElements(driver, this);
	}
	
	public void loginClick() {
		wait.until(ExpectedConditions.visibilityOf(login_link));
		login_link.click();
	}
	
	public void username_field(String value) {
		username.sendKeys(value);
	}
	
	public void password_field(String value) {
		password.sendKeys(value);
	}
	
	public void login() {
		login_button.click();
	}
	
	public static void navigateTo_homeUI() {
		driver.get(prop.getProperty("URL"));
	}
	
	public void reviews() {
		reviews_link.click();
	}
	
	public void ratingStar() {
		Actions a = new Actions(driver);
		a.moveToElement(rating_star).click().build().perform();
	}
	
	public void select_HealthInsurance() {
		select_dropdown.click();
		select_dropdown_value.click();
	}
	
	public void select_writeRiview(String value) {
		write_review_textarea.click();
		write_review_textarea.sendKeys(value);
	}
	
	public void submitReview() {
		submit_button.click();
	}
	
	public String getReviewText() {
		return your_review_text.getText();
	}
}