package ar.com.manutesting.utiles.baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManejoBaseDeDatos {
	
	protected PreparedStatement pstm = null;
	protected ResultSet rs = null;
	
	/**
	 * @Definición:  Devuelve todos los registros de una tabla de la base de datos.
	 * @param consulta -- Tabla de la base de datos sobre la que queremos todos los registros 
	 * @return -- Todos los registros de la tabla
	 * @throws Exception
	 */
	public ResultSet obtenerTodosLosRegistrosDeUnaTablaDeLaBaseDeDatos(String consulta) throws Exception {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement(consulta);
		rs = pstm.executeQuery();
		return rs;
	}
	
	/**
	 * @Definición:  Cerrar conexion con la base de datos
	 * @throws Exception
	 */
	public void cerrarConexion() throws Exception{
		if(rs!=null)rs.close();
		if(pstm!=null)pstm.close();
		FactoryConnection.getinstancia().releaseConn();
	}
	
	
	/**
	 * @Definición:  Convierte el ResultSet en un listado de mapa (Clave, valor)
     * 				Cada registro de la base de datos es una fila de la lista que contiene un mapa de columna de la base - valor
	 * @param resultado -- El ResultSet que nos devuelve la consulta a la base 
	 * @return -- Listado de Mapa(Columna de la base - Valor)
	 * @throws SQLException
	 */
	public List<Map<String,String>> convertirResultSetAListadoDeMapas(ResultSet resultado) throws SQLException {
		ResultSetMetaData metaDataResultado = resultado.getMetaData();
		List<String> listadoDeColumnas = new ArrayList<String>(metaDataResultado.getColumnCount());
		List<Map<String,String>> mapaConResultados = new ArrayList<Map<String,String>>();
		
		for(int i = 1; i <= metaDataResultado.getColumnCount(); i++){
		    listadoDeColumnas.add(metaDataResultado.getColumnName(i));
		 }
		
	    while(resultado.next()){    
	    	Map<String,String> row = new HashMap<String, String>(listadoDeColumnas.size());
	        for(String columnas : listadoDeColumnas) {
	            row.put(columnas, resultado.getString(columnas));
	        }
	        mapaConResultados.add(row);
	    }
	    
	    return mapaConResultados;
	}
	    

	/**
	 * @Definición: Devuelve los registros de la consulta realizada a la base de datos
	 * @param consulta -- Consulta sql que se desea ejecutar
	 * @return -- Retorna un resultset con el resultado de la consultado.
	 * @throws SQLException
	 * @throws Exception
	 */
	public ResultSet realizarConsultaSql(String consulta) throws SQLException, Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement(consulta);
		rs=pstm.executeQuery();

		if(pstm!=null)pstm.close();
		FactoryConnection.getinstancia().releaseConn();
		
		return rs;
	}
}
