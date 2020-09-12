package ar.com.manutesting.utiles.driversBrowser;

import ar.com.manutesting.utiles.enums.Navegador;

public class DriverManagerFactory {
	
	public static DriverManager getManager(Navegador navegador, Boolean modoEjecucion) {
		
		DriverManager driverManager = null;
		
		if (modoEjecucion) {
			driverManager = new RemoteDriverManager();
		}else {
			switch (navegador) {
			case CHROME:
				driverManager = new ChromeDriverManager();
				break;			
			case FIREFOX:
				driverManager = new FirefoxDriverManager();
				break;
			case EDGE:
				driverManager = new EdgeDriverManager();
				break;
			case HEADLESS:
				driverManager = new ChromeHeadlessDriverManager();
				break;
			default:
				throw new IllegalArgumentException("Driver no encontrado entre los navegadores: "+ navegador);
			}
		}
		
		return driverManager;
	}

}
