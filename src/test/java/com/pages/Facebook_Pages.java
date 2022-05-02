package com.pages;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class Facebook_Pages extends Driver{
	public static final String filename = null;
	public static ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public static Properties prop = readPropertiesFile.readPropertiesFile(filename);

	@FindBy(id = "email")
	WebElement email_Field;
	
	@FindBy(id = "passContainer")
	WebElement password_Field;
	
	@FindBy(xpath = "//button[contains(text(),'Log In')]")
	WebElement login_button;
	
	@FindBy(xpath = "//span[contains(text(),'What')]")
	WebElement createPost_field;
	
	@FindBy(xpath = "//div[contains(text(),'What')]")
	WebElement createPost_TextArea;
	
	@FindBy(xpath = "//span[contains(text(),'Post')]")
	WebElement post_button;

	public Facebook_Pages() {
		PageFactory.initElements(driver, this);
	}
	
	public static void navigateTo_LoginUI() {
		driver.get(prop.getProperty("URL_FB"));
	}

	public void username_field(String value) {
		email_Field.sendKeys(value);
	}
	
	public void password_field(String value) {
		password_Field.click();
		password_Field.sendKeys(value);
	}
	
	public void login() {
		login_button.click();
	}
	
	public void statusPostField() {
		createPost_field.click();
	}
	
	public void StatusMessageDraftTextArea(String value) {
		createPost_TextArea.sendKeys(value);
	}
	
	public void post() {
		post_button.click();
	}
}
