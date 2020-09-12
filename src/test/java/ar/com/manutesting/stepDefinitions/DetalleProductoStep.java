package ar.com.manutesting.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.manutesting.paginas.DetalleProducto;
import ar.com.manutesting.utiles.soporte.Soporte;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j;

@Log4j
public class DetalleProductoStep{
	
	@Autowired
	private DetalleProducto paginaDetalleProducto;
	@Autowired
	private Soporte soporte;
	
	
	@Then("Visualiza estos valores")
	public void visualiza_estos_valores(DataTable dataTable) throws Exception {
		try {
			List<Map<String,String>> tablaDatos = dataTable.asMaps(String.class, String.class);	
			for (int i = 0; i < tablaDatos.size(); i++) {
				paginaDetalleProducto.validarPrecio(tablaDatos.get(i).get("precio"));
				paginaDetalleProducto.validarEstado(tablaDatos.get(i).get("estado"));
				paginaDetalleProducto.validarResolucion(tablaDatos.get(i).get("resolucion"));
			}
		} catch (Exception e) {
			String message = soporte.getMensajeDeExcepcion(e);
			soporte.adherirErrorAlReporteAllure(message);
			log.error(message);
			throw e;
		}
	}
}
