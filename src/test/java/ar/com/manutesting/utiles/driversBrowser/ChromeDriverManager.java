package ar.com.manutesting.utiles.driversBrowser;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager{
	
	 private static String pathFormat = System.getProperty("user.dir") + "/src/test/resources/drivers/%s";
	 
	@Override
	protected void createDriver() {
		System.setProperty("webdriver.chrome.driver",String.format(pathFormat, "chromedriver.exe"));
		driver = new ChromeDriver();
	}

}
