package ar.com.manutesting.paginas;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.junit.Assert;

@Component
@Scope("cucumber-glue")
public class ListadoProductos extends PaginaBase{

	@Autowired
	public ListadoProductos(WebDriver pDriver) {super(pDriver);}
	
	private static String productoPhilips = "//div[@class='ui-search-item__group ui-search-item__group--title']//a[@title='%s']";
	private static String iconoSegundaPagina = "//a[@class='andes-pagination__link ui-search-link' and text()='%s']";
	private static final String elementosListados = "//li[@class='ui-search-layout__item']";
	private static String elementosListadosQueCoincideConPalabraClave = "//li[@class='ui-search-layout__item']//h2[contains(text(),'%s')]";

	public void seleccionarProductoDelListado(String producto) throws Exception {
		clickElemento(String.format(productoPhilips, producto), producto);		
	}

	public void avanzarDePagina(String numeroPagina) throws Exception {
		clickElemento(String.format(iconoSegundaPagina, numeroPagina), "Icono pagina numero "+numeroPagina);
	}

	public void verificarProductosListados(Integer cantidadRegistros, String palabraClave) throws Exception {
		Assert.assertTrue("Los productos listados no coinciden con el numero esperado de "+cantidadRegistros, cantidadDeElementos(elementosListados) >= cantidadRegistros);
		Assert.assertTrue("El "+cantidadRegistros * 0.5+ "% de los productos listados no contienen el texto esperado "+palabraClave,cantidadDeElementos(String.format(elementosListadosQueCoincideConPalabraClave, palabraClave)) > (cantidadRegistros * 0.5));
	}

}
