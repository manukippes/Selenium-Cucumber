package ar.com.manutesting.utiles.soporte;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

@Component
@Scope("cucumber-glue")
public class Soporte {
	
	@Autowired
	protected WebDriver driver;
	
	/**
	 * @Definición:  Devuelve la ruta completa (C:\\....) de la carpeta image dentro de recursos.
	 * @return -- Ruta completa. Ejemplo: C:\Emmanuel\SQA_Automatizaciones\template\src\test\resources\image
	 * @throws Exception
	 */
	public String getRutaDeCarpetaImage() throws Exception {
		String path = "src/test/resources/image";
		File file = new File(path);
		return file.getAbsolutePath()+"\\";
	}
	
	/**
	 * @Devuelve la ruta completa (C:\\....) de la carpeta drivers dentro de recursos.
	 * @return -- Ruta completa. Ejemplo: C:\Emmanuel\SQA_Automatizaciones\template\src\test\resources\drivers
	 * @throws Exception
	 */
	public String getRutaDeCarpetaDrivers() throws Exception {
		String path = "src/test/resources/drivers";
		File file = new File(path);
		return file.getAbsolutePath()+"\\";
	}
	
	/**
	 * @Definición: Según la excepción que recibe devuelve un mensaje personalizado junto al mensaje y la causa propia de la excepcion.
	 * @param e -- Excepcion que se produjo.
	 * @return -- Retorna un mensaje en formato amigable para el usuario.
	 * @throws Exception
	 */
	@Attachment(value = "{0}", type = "text/plain")
	public String getMensajeDeExcepcion(Exception e) throws Exception {
		String mensaje = e.getMessage();
		String causa = "";
		if (e.getCause() != null) {
			causa = e.getCause().toString();
		}
		if (e instanceof NoSuchElementException) {
			return String.format(
					"No se encontró el elemento en la página con el locator especificado. %n *Mensaje*: %1$s %n *Causa*: %2$s %n",
					mensaje, causa);
		} else if (e instanceof ElementNotVisibleException) {
			return String.format(
					"Elemento se encontró en la página pero no está visible. Verificar si otro elemento lo está tapando. %n *Mensaje*: %1$s %n *Causa*: %2$s %n",
					mensaje, causa);
		} else if (e instanceof TimeoutException) {
			return String.format(
					"Se superó el tiempo de espera del elemento en el DOM de la página. %n *Mensaje*: %1$s %n *Causa*: %2$s %n",
					mensaje, causa);
		} else if (e instanceof StaleElementReferenceException) {
			return String.format(
					"El elemento se encontraba en el DOM de la página pero ya no. %n *Mensaje*: %1$s %n *Causa*: %2$s %n",
					mensaje, causa);
		} else {
			return String.format("Ocurrio un error inesperado. %n Mensaje: %1$s %n Causa: %2$s %n", mensaje, causa);
		}
	}
	
	/**
	 * @Definición:  Toma una captura de la pantalla y la devuelve en formato byte[] para ser usanda dentro del reporte de Cucumber.
	 * @return -- Retorna la captura de pantalla.
	 * @throws Exception
	 */
	@Attachment(value = "CucumberReport - Captura de pantalla", type = "image/png")
	public byte[] capturarPantallaParaReporteCucumber() throws Exception {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		return screenshot;
	}
	
	/**
	 * @Definición:  Toma una captura de la pantalla y la guarda en el proyecto.
     *				Ruta donde la guarda: ./target/imagenes/
	 * @param nameFile -- Nombre que se le quiere dar a la captura de pantalla. Debe contentar la extensión. Ejemplo: nombreCaptura.png
	 * @return -- Retorna el path de la captura de pantalla. Donde se guardó.
	 * @throws Exception
	 */
	public String capturarPantallaYGuardarla(String nameFile) throws Exception {
		final File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String urlDestino = "./target/imagenes/" + nameFile;
		FileUtils.copyFile(screenshot, new File(urlDestino));
		return urlDestino;
	}
	
	/**
	 * @Definición: Adhiere mensaje de error y saca captura del error.
	 * @param mensaje -- Mensaje de error a mostar en Allure
	 */
	public void adherirErrorAlReporteAllure(String mensaje) {
		Allure.addAttachment("Error", mensaje);
		Allure.addAttachment("Allure - Captura de Pantalla", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
	}
	

	/**
	 * @Definición: Adhiere captura de pantalla al reporte de Allure.
	 */
	public void adherirCapturaAlReporteAllure() {
		Allure.addAttachment("Allure - Captura de Pantalla", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
	}
	
	

}
