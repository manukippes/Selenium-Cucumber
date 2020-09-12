package ar.com.manutesting.utiles.driversBrowser;

import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;

import ar.com.manutesting.utiles.enums.Navegador;

public class RemoteDriverManager extends DriverManager{
	
	@Value("${navegador}")
	private Navegador browser;
	
	@Value("${servidorRemotoUrl}")
	private String urlServidor;
	
	@Override
	protected void createDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		try {
			capabilities.setBrowserName(browser.name());
			driver = new RemoteWebDriver(URI.create(urlServidor).toURL(), capabilities);
		} catch (MalformedURLException e) {
			throw e;
		}
	}

}
