package ar.com.manutesting.stepDefinitions;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.manutesting.paginas.ListadoProductos;
import ar.com.manutesting.utiles.soporte.Soporte;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;

@Log4j
public class ListadoProductosStep {
	
	@Autowired
	private Soporte soporte;
	@Autowired
	private ListadoProductos listadoProductos;
	
	@When("ingresar al detalle de la tv {string}")
	public void ingresar_al_detalle_de_la_tv(String producto) throws Exception {
		try {
			listadoProductos.seleccionarProductoDelListado(producto);
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);	
			throw e;
		}
	}
	
	@When("Se dirige a la {string} pagina del listado")
	public void se_dirige_a_la_segunda_pagina_del_listado(String numeroPagina) throws Exception {
		try {
			listadoProductos.avanzarDePagina(numeroPagina);
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);	
			throw e;
		}
	}
	
	@Then("Se visualizan {int} productos que contienen la palabra {string}")
	public void se_visualizan_productos_que_contienen_la_palabra(int cantidadRegistros, String palabraClave) throws Exception {
		try {
			listadoProductos.verificarProductosListados(cantidadRegistros, palabraClave);
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);	
			throw e;
		}
	}
}
