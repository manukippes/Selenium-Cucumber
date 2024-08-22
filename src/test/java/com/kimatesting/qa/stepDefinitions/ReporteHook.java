package com.kimatesting.qa.stepDefinitions;

import java.util.UUID;
import com.kimatesting.qa.utils.enums.TomarCaptura;
import com.kimatesting.qa.utils.soporte.Soporte;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ReporteHook{
	private Soporte soporte;
	private TomarCaptura tomarCaptura;

	private String nombreEscenario;
	private String estadoEscenario;


	@Before
	public void iniciarEscenarioLog(Scenario escenario) {
		nombreEscenario = escenario.getName().toUpperCase();
	}

	@After
	public void tomarCapturaCuandoFallaEscenario(Scenario escenario) throws Exception {
		if (escenario.isFailed() && TomarCaptura.fallaEscenario == tomarCaptura) {
			soporte.adherirCapturaAlReporteAllure();
			capturarPantalla(escenario);
		}
	}

	@After
	public void finalizaEscenarioLog(Scenario escenario) throws Exception {
		try {
			nombreEscenario = escenario.getName().toUpperCase();
			estadoEscenario = escenario.getStatus().toString();
			if (escenario.isFailed())
			if (estadoEscenario == "PASSED") {
				soporte.adherirCapturaAlReporteAllure();
				capturarPantalla(escenario);
			}
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			throw e;
		}
	}

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

	public void capturarPantalla(Scenario escenario) throws Exception {
	try {
		//escenario.embed(soporte.capturarPantallaParaReporteCucumber(), "image/png", UUID.randomUUID().toString());
	} catch (Exception e) {
		String message = soporte.getMensajeDeExcepcion(e);
		soporte.adherirErrorAlReporteAllure(message);
		throw e;
	}

	}

}
