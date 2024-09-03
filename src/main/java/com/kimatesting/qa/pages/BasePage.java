package com.kimatesting.qa.pages;

import java.time.Duration;
import java.util.List;

import com.kimatesting.qa.utils.LoggerHelper;
import com.kimatesting.qa.enums.FindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected static WebDriver driver;
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

	protected WebElement findElementByXpath(String locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	protected WebElement findElementByCssSelector(String locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
	}

	protected List<WebElement> findElementsByXpath(String locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	}

	public void clickElement(String locator, String elementName,  FindBy findBy) {
		scrollToElement(locator, elementName, findBy);
		if (FindBy.css.equals(findBy)) findElementByCssSelector(locator).click();
		if (FindBy.xpath.equals(findBy)) findElementByXpath(locator).click();
		LoggerHelper.log.info("Click on the element: "+elementName);
	}
	
	public void sendTextOfElement(String locator, String text, String elementName, FindBy findBy) {
		scrollToElement(locator, elementName, findBy);
		WebElement element = null;
		if (FindBy.css.equals(findBy)) element = findElementByCssSelector(locator);
		if (FindBy.xpath.equals(findBy)) element = findElementByXpath(locator);
		element.clear();
		element.sendKeys(text);
		LoggerHelper.log.info("Type text: "+text+" on the element: "+elementName);
	}

	public void scrollToElement(String locator, String elementName, FindBy findBy) {
		if (FindBy.css.equals(findBy)) jsExecutor.executeScript("arguments[0].scrollIntoView(false);", findElementByCssSelector(locator));
		if (FindBy.xpath.equals(findBy)) jsExecutor.executeScript("arguments[0].scrollIntoView(false);", findElementByXpath(locator));
		LoggerHelper.log.info("Scroll to element: "+elementName);
	}


	/*** UTILS	***/
	protected Integer elementCount(String locator) {
		return findElementsByXpath(locator).size();
	}
	protected void waitFixTime() throws InterruptedException {Thread.sleep(1000);}

	/*** GET INFO	***/
	protected String getTitle() {
		return driver.getTitle();
	}

	protected String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public static String takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
}
