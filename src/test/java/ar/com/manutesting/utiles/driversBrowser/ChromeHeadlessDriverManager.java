package ar.com.manutesting.utiles.driversBrowser;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeHeadlessDriverManager extends DriverManager{
	
	private static String pathDriver = "./src/test/resources/drivers/%s"+".exe";


	@Override
	protected void createDriver() {
		System.setProperty("webdriver.chrome.driver", String.format(pathDriver, "chromeDriver"));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}

}
