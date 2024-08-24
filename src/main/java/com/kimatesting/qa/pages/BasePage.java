package com.kimatesting.qa.pages;

import java.time.Duration;
import java.util.List;

import com.kimatesting.qa.utils.LoggerHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;
	private static final Duration TIMEOUT = Duration.ofSeconds(10);

	public BasePage(WebDriver pDriver){
        this.driver = pDriver;
		wait = new WebDriverWait(driver, TIMEOUT);
        jsExecutor = (JavascriptExecutor) driver;
    }
	
	public void navigateTo(String url) {
		driver.get(url);
		LoggerHelper.log.info("Navigate to url "+url);
	}

	private WebElement findElementByXpath(String locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	private List<WebElement> findElementsByXpath(String locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	}

	public void clickElement(String locator, String elementName) throws Exception {
		scrollToElement(locator, elementName);
		findElementByXpath(locator).click();
		LoggerHelper.log.info("Click on the element: "+elementName);
	}
	
	public void sendTextOfElement(String locator, String text, String elementName) throws Exception{
		scrollToElement(locator, elementName);
		findElementByXpath(locator).clear();
		findElementByXpath(locator).sendKeys(text);
		LoggerHelper.log.info("Type text: "+text+" on the element: "+elementName);
	}

	public void scrollToElement(String locator, String elementName) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", findElementByXpath(locator));
		LoggerHelper.log.info("Scroll to element: "+elementName);
	}

	/*** UTILS	***/
	protected Integer elementCount(String locator) throws Exception {
		return findElementsByXpath(locator).size();
	}

	protected String getTitle() {
		return driver.getTitle();
	}
}
