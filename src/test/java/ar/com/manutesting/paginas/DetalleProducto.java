package ar.com.manutesting.paginas;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class DetalleProducto extends PaginaBase {

	@Autowired
	public DetalleProducto(WebDriver driver){super(driver);}
	
	private final static String precioProducto = "//span[@itemprop='offers']//span[@class='price-tag-fraction']";
	private final static String estadoProducto = "(//span[@class='ui-pdp-subtitle'])[1]";
	private final static String resolucionProducto = "(//li[@class='ui-pdp-features__item'])[1]";

	public void validarPrecio(String precio) throws Exception {
		validExactTextOfElement(precioProducto, precio, "Precio");
	}

	public void validarEstado(String estado) throws Exception {
		validTextContainsOfElement(estadoProducto, estado, "Estado");
	}

	public void validarResolucion(String resolucion) throws Exception {
		validExactTextOfElement(resolucionProducto, resolucion, "Resolucion");
	}
}
