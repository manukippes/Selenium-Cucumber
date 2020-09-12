package ar.com.manutesting.stepDefinitions;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.manutesting.paginas.Landing;
import ar.com.manutesting.utiles.soporte.Soporte;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;

@Log4j
public class LandingStep{
	
	@Autowired
	private Landing paginaLanding;
	@Autowired
	private Soporte soporte;
	
	
	@Given("Un cliente ingresa a la aplicacion de MercadoLibre")
	public void un_cliente_ingresa_a_la_aplicacion_de_MercadoLibre() throws Exception {
		try {
			paginaLanding.navegarAMercadoLibre();
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);	
			throw e;
		}
	}

	@When("en la categoria tecnologia selecciona TVS")
	public void en_la_categoria_tecnologia_selecciona_TVS() throws Exception {
		try {
			paginaLanding.clickCategoria();
			paginaLanding.clickTecnologia();
			paginaLanding.clickTV();
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);	
			throw e;
		}
	}
	
	@When("Ingresa en el campo de busqueda la palabra {string} y la selecciona del combo")
	public void ingresa_en_el_campo_de_busqueda_la_palabra_y_la_selecciona_del_combo(String palabraClave) throws Exception {
		try {
			paginaLanding.ingresarPalabraEnElCampoDeBusqueda(palabraClave);
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);	
			throw e;
		}
	}
	
}
