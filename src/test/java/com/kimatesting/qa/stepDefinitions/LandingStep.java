package com.kimatesting.qa.stepDefinitions;

import com.kimatesting.qa.utils.soporte.Soporte;
import io.cucumber.java.en.Given;

public class LandingStep{
	private Soporte soporte;

	@Given("Un cliente ingresa a la aplicacion de MercadoLibre")
	public void un_cliente_ingresa_a_la_aplicacion_de_MercadoLibre() throws Exception {
		try {
			//TODO
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			throw e;
		}
	}
	
}
