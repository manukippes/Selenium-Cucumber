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
	
	/**
	 * @Definición:  Abre un navegador en la url que le especificamos.
	 * @param url
	 * @throws Exception
	 */
	public void navegarA(String url) throws Exception {
		driver.get(url);
		log.info("Se ingresó a la url: "+url);
	}


	/**
	 * @Definición:  Busca un elemento dentro del DOM de la página según el xpath que le pasamos.
     *				Espera que el elemento esté visible hasta que se cumpla el tiempo que definimos para wait.
	 * @param locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @return WebElement Un elemento de la página con el cual interacturar.
	 * @throws Exception
	 */
	private WebElement encontrarElemento(String locator) throws Exception {
		if (demo) {Thread.sleep(1000);}
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	/**
	 * @Definición:  Busca elementos dentro del DOM de la página según el xpath que le pasamos.
     *				Espera que el elemento esté visible hasta que se cumpla el tiempo que definimos para wait.
	 * @param locator -- Xpath por el cual se van a encontrar los elementos dentro de la página.
	 * @return Lis<WebElement> Elementos de la página con los cuales interacturar.
	 * @throws Exception
	 */
	private List<WebElement> encontrarElementos(String locator) throws Exception {
		if (demo) {Thread.sleep(1000);}
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
	}
	

	/**
	 * @Definición:  Hace click sobre un elemento de la página que fue encontrado según el xpath que le pasamos.
	 * @param locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 * @throws Exception
	 */
	public void clickElemento(String locator, String nombreElemento) throws Exception {
		hacerScrollHaciaUnElemento(locator, nombreElemento);
		encontrarElemento(locator).click();
		log.info("Se hizo click en el elemento: "+nombreElemento);
	}
	

	/**
	 * @Definición:  Escribe el texto que le pasamos dentro de un elemento de la página.
	 * @param locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @param texto -- Texto a ingresar dentro del elemento.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 * @throws Exception
	 */
	public void escribirTextoEnElemento(String locator, String texto, String nombreElemento) throws Exception{
		hacerScrollHaciaUnElemento(locator, nombreElemento);
		encontrarElemento(locator).clear();
		encontrarElemento(locator).sendKeys(texto);
		log.info("Se escribió: "+texto+" sobre el elemento: "+nombreElemento);
	}
	

	/**
	 * @Definición:  Presiona una tecla del teclado sobre un elemento de la página.
	 * @param locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @param tecla -- Tecla del teclado a presionar. Proviene del ENUM Teclas.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 * @throws Exception
	 */
	public void presionarUnaTecla(String locator, Tecla tecla, String nombreElemento) throws Exception {
		switch (tecla) {
		case ABAJO:
			encontrarElemento(locator).sendKeys(Keys.ARROW_DOWN);
			break;
		case ENTER:
			encontrarElemento(locator).sendKeys(Keys.ENTER);
			break;
		default:
			break;
		}
		log.info("Se presionó la tecla "+tecla+" sobre el elemento: "+nombreElemento);
	}
	
	
	/**
	 * @Definición:  Valida el texto que tiene un elemento de la página.
	 * @param locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @param text -- Texto a validar.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 * @throws Exception
	 */
	public void validarTextoDeElemento(String locator, String text, String nombreElemento) throws Exception {
		hacerScrollHaciaUnElemento(locator, nombreElemento);
		Assert.assertEquals("El valor: \'"+text+"\' no se encuentra dentro de: \'"+encontrarElemento(locator).getText()+"\'", text, encontrarElemento(locator).getText());
		log.info("Se validó que "+text+" se visualiza en el elemento: "+nombreElemento);
	}
	
	
	/**
	 * @Definición:  Valida que un elemento de la página contenga un texto determinado.
	 * @param locator -- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @param textoEsperado -- Texto a validar.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 * @throws Exception
	 */
	public void validarQueElementoContengaUnTexto(String locator, String textoEsperado, String nombreElemento) throws Exception {
		String textoQueSeVisualiza = encontrarElemento(locator).getText();
		Assert.assertTrue("El valor: \'"+textoEsperado+"\' no se encuentra dentro de: \'"+textoQueSeVisualiza+"\'", textoQueSeVisualiza.contains(textoEsperado));
		log.info("Se validó que "+textoEsperado+" se visualiza dentro del elemento con locator: "+nombreElemento);
	}

	/**
	 * @Definición:  Presiona un combo de la página y busca un elemento dentro de el.
     *				Hace click en el combo y luego click en el valor dentro de el.
	 * @param combo -- Xpath del combo a presionar.
	 * @param value -- Xpath del valor a seleccionar dentro del combo.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 */
	public void seleccionarUnElementoDeUnCombo(String combo, String value, String nombreElemento) throws Exception {
		encontrarElemento(combo).click();
		encontrarElemento(value).click();
		log.info("Se seleccionó el valor: "+value+" del elemento: "+nombreElemento);
	}
	
	/**
	 * @Definición:  Cantidad de elementos que coinciden con el locator
	 * @param locator -- Xpath por el cual se van a encontrar los elementos dentro de la página.
	 * @return int -- Numero de elementos encontrados 
	 * @throws Exception
	 */
	public Integer cantidadDeElementos(String locator) throws Exception {
		return encontrarElementos(locator).size();
	}
	
	/**
	 * @Definición:  Hace scroll dentro la página hasta el elemento que le pasamos.
     *				Si ya se visualiza el elemento no hace el scroll.
	 * @param locator	-- Xpath por el cual se va a encontrar el elemento dentro de la página.
	 * @param nombreElemento -- Nombre con el cual se va a representar el elemento en el log
	 * @throws Exception
	 */
	public void hacerScrollHaciaUnElemento(String locator, String nombreElemento) throws Exception {
		js.executeScript("arguments[0].scrollIntoView(false);", encontrarElemento(locator));
		log.info("Se hizo scroll hacia el elemento: "+nombreElemento);
	}
	

	/**
	 * @Definición: Espera un tiempo fijo en milisegundos. NO SE DEBE UTILIZAR EN ABUSO, SOLO CASO EXTREMO.
	 * @param milisegundos -- Cantidad de milisegundos que queremos que espere
	 * @throws InterruptedException
	 */
	public void esperarMilisegundos(long milisegundos) throws InterruptedException {
		Thread.sleep(milisegundos);		
	}
	
	/**
	 * @Definición: Espera que un elemento se visualice en el DOM por un tiempo determinado
	 * @param locator
	 * @param tiempo
	 * @throws Exception
	 */
	public void esperarElemento(String locator, long tiempo) throws Exception{
		WebDriverWait esperar = new WebDriverWait(driver, tiempo);
		esperar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		log.info("Se esperó al elemento "+locator+" por "+tiempo+" segundos.");
	}
}
