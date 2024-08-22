package com.kimatesting.qa.utils.baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	private Connection conn = null;
	private int cantConn = 0;

	private static FactoryConnection instancia; // para tener solo y exclusivamente un único objeto de una clase.

	private FactoryConnection() throws InstantiationException, IllegalAccessException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static FactoryConnection getinstancia() throws InstantiationException, IllegalAccessException { // devuelve la unica conexion
		if (FactoryConnection.instancia == null) {
			FactoryConnection.instancia = new FactoryConnection();
		}
		return FactoryConnection.instancia;
	}

	public Connection getConn() throws Exception {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("conexion","usuario", "clave");
				//conexion= String de conexion(Ejemplo: jdbc:oracle:thin:@host:puerto:base), usuario=nombreUsuario, clave=claveUsuario
			}
		} catch (Exception e) {
			throw e;
		}
		cantConn++;
		return conn;
	}

	public void releaseConn() throws Exception {
		try {
			cantConn--;
			if (cantConn == 0) {
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e1) {
			throw e1;
		}
	}
}