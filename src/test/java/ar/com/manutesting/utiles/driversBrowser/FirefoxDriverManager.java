package ar.com.manutesting.utiles.driversBrowser;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{
	
	private static String pathDriver = "./src/test/resources/drivers/%s"+".exe";

	@Override
	protected void createDriver() {
		System.setProperty("webdriver.gecko.driver", String.format(pathDriver, "geckodriver"));
		driver = new FirefoxDriver();
	}

}
