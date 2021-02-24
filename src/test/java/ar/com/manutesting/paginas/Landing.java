package ar.com.manutesting.paginas;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.manutesting.utiles.enums.Tecla;

@Component
@Scope("cucumber-glue")
public class Landing extends PaginaBase {
	
	@Autowired
	public Landing (WebDriver driver) {super(driver);}

	private final static String categoria = "//a[@class='nav-menu-categories-link' and text()='Categorías']";
	private final static String tecnologia = "//ul//li//a[text()='Tecnología']";
	private final static String tv = "//h2[@class='nav-categs-detail__title']//a[text()='TVs']";
	private final static String campoBusqueda = "//input[@name='as_word']";
	
	@Value("${url}")
	private String urlConfig;

	public void navegarAMercadoLibre() throws Exception {
		navegarA(urlConfig);
		driver.manage().window().maximize();
	}
	
	public void clickCategoria() throws Exception {
		clickElement(categoria, "Categoria");
	}

	public void clickTecnologia() throws Exception {
		clickElement(tecnologia, "Tecnologìa");
	}

	public void clickTV() throws Exception {
		clickElement(tv, "TVs");
	}

	public void ingresarPalabraEnElCampoDeBusqueda(String palabraClave) throws Exception {
		sendTextOfElement(campoBusqueda, palabraClave, "Campo de Busqueda");	
		pressKey(campoBusqueda, Tecla.ENTER, "Tecla Enter");
	}
}
