package ar.com.manutesting.paginas;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.manutesting.utiles.enums.Tecla;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
@Scope("cucumber-glue")
public class PaginaBase {

	protected WebDriver driver;
	
	@Autowired
	protected WebDriverWait wait;
	
	protected JavascriptExecutor js;
	
	@Value("${demo}")
	private boolean demo;
	
	@Autowired
	public PaginaBase(WebDriver pDriver){
        driver = pDriver;
        js = (JavascriptExecutor) driver;
    }
	
	public void navegarA(String url) throws Exception {
		driver.get(url);
		log.info("Se ingresó a la url: "+url);
	}


	private WebElement findElementByXpath(String locator) throws Exception {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	private List<WebElement> findElementsByXpath(String locator) throws Exception {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	}
	

	public void clickElement(String locator, String nombreElemento) throws Exception {
		scrollToElement(locator, nombreElemento);
		findElementByXpath(locator).click();
		log.info("Se hizo click en el elemento: "+nombreElemento);
	}
	
	public void sendTextOfElement(String locator, String texto, String nombreElemento) throws Exception{
		scrollToElement(locator, nombreElemento);
		findElementByXpath(locator).clear();
		findElementByXpath(locator).sendKeys(texto);
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
	}
	

	public void pressKey(String locator, Tecla tecla, String nombreElemento) throws Exception {
		switch (tecla) {
		case ABAJO:
			findElementByXpath(locator).sendKeys(Keys.ARROW_DOWN);
			break;
		case ENTER:
			findElementByXpath(locator).sendKeys(Keys.ENTER);
			break;
		default:
			break;
		}
		log.info("Se presionó la tecla "+tecla+" sobre el elemento: "+nombreElemento);
	}
	
	
	public void validExactTextOfElement(String locator, String text, String nombreElemento) throws Exception {
		scrollToElement(locator, nombreElemento);
		Assert.assertEquals("El valor: \'"+text+"\' no se encuentra dentro de: \'"+findElementByXpath(locator).getText()+"\'", text, findElementByXpath(locator).getText());
		log.info("Se validó que "+text+" se visualiza en el elemento: "+nombreElemento);
	}
	
	
	public void validTextContainsOfElement(String locator, String textoEsperado, String nombreElemento) throws Exception {
		String textoQueSeVisualiza = findElementByXpath(locator).getText();
		Assert.assertTrue("El valor: \'"+textoEsperado+"\' no se encuentra dentro de: \'"+textoQueSeVisualiza+"\'", textoQueSeVisualiza.contains(textoEsperado));
		log.info("Se validó que "+textoEsperado+" se visualiza dentro del elemento con locator: "+nombreElemento);
	}

	public Integer elementCount(String locator) throws Exception {
		return findElementsByXpath(locator).size();
	}
	
	public void scrollToElement(String locator, String nombreElemento) throws Exception {
		js.executeScript("arguments[0].scrollIntoView(false);", findElementByXpath(locator));
		log.info("Se hizo scroll hacia el elemento: "+nombreElemento);
	}
}
