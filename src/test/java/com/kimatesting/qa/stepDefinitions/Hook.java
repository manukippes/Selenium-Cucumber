package com.kimatesting.qa.stepDefinitions;

import com.kimatesting.qa.utils.DriverManager;
import com.kimatesting.qa.utils.LoggerHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

	@Before
	public void init() {
		//nombreEscenario = escenario.getName().toUpperCase();
		DriverManager.initDriver();
		LoggerHelper.createLogger();
	}

	@After
	public void turnDown() {
		DriverManager.closeDriver();
	}

//	@After
//	public void tomarCapturaCuandoFallaEscenario(Scenario escenario) throws Exception {
//		if (escenario.isFailed() && TomarCaptura.fallaEscenario == tomarCaptura) {
//			soporte.adherirCapturaAlReporteAllure();
//			capturarPantalla(escenario);
//		}
//	}
//
//	@After
//	public void finalizaEscenarioLog(Scenario escenario) throws Exception {
//		try {
//			nombreEscenario = escenario.getName().toUpperCase();
//			estadoEscenario = escenario.getStatus().toString();
//			if (escenario.isFailed())
//			if (estadoEscenario == "PASSED") {
//				soporte.adherirCapturaAlReporteAllure();
//				capturarPantalla(escenario);
//			}
//		} catch (Exception e) {
//			String message = soporte.getMensajeDeExcepcion(e);
//			soporte.adherirErrorAlReporteAllure(message);
//			throw e;
//		}
//	}

	/*
	@After
	public void tomarCapturaCuandoFinalizaEscenario(Scenario escenario) throws Exception {
		if (TomarCaptura.finalizaEscenario == tomarCaptura) {
			capturarPantalla(escenario);
		}
	}

	@AfterStep
	public void tomarCapturaPorCadaPaso(Scenario escenario) throws Exception {
		if (TomarCaptura.cadaPaso == tomarCaptura) {
			capturarPantalla(escenario);
		}
	}
	*/

//	public void capturarPantalla(Scenario escenario) throws Exception {
//	try {
//		//escenario.embed(soporte.capturarPantallaParaReporteCucumber(), "image/png", UUID.randomUUID().toString());
//	} catch (Exception e) {
//		String message = soporte.getMensajeDeExcepcion(e);
//		soporte.adherirErrorAlReporteAllure(message);
//		throw e;
//	}
//
//	}

}
