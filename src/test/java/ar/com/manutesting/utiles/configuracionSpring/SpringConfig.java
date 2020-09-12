package ar.com.manutesting.utiles.configuracionSpring;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import ar.com.manutesting.utiles.driversBrowser.DriverManager;
import ar.com.manutesting.utiles.driversBrowser.DriverManagerFactory;
import ar.com.manutesting.utiles.enums.Navegador;

@Configuration	
public class SpringConfig {
	
	@Value("${navegador}")
	private Navegador navegador;
	
	@Value("${timeOut}")
	private long timeOut;
	
	@Value("${ejecucionRemota:false}")
	protected Boolean modoEjecucion;
	
	@Autowired
	@Lazy
	private DriverManager driverManager;
	
	@Scope("cucumber-glue")
	@Bean(destroyMethod="quit")
	public WebDriver getDriver() throws MalformedURLException {
		driverManager = DriverManagerFactory.getManager(navegador, modoEjecucion);
		return driverManager.getDriver();
	}
	
	@Scope("cucumber-glue")
	@Bean
	@DependsOn("getDriver")
	public WebDriverWait waitFor() throws MalformedURLException {
		return new WebDriverWait(getDriver(), timeOut);
	}}
