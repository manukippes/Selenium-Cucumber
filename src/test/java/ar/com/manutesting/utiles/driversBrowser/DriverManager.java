package ar.com.manutesting.utiles.driversBrowser;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public abstract class DriverManager {

	protected WebDriver driver;
	
	protected abstract void createDriver() throws MalformedURLException;
	
	public WebDriver getDriver() throws MalformedURLException {
		if(driver == null) {
			createDriver();
		}
		
		return driver;
	}
	
}
