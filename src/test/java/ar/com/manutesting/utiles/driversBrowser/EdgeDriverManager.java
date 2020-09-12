package ar.com.manutesting.utiles.driversBrowser;

import org.openqa.selenium.edge.EdgeDriver;


public class EdgeDriverManager extends DriverManager{

	private static String pathDriver = "./src/test/resources/drivers/%s"+".exe";

	@Override
	protected void createDriver() {
		System.setProperty("webdriver.edge.driver", String.format(pathDriver, "msedgedriver"));
		driver = new EdgeDriver();
	}

}
