# Selenium-Cucumber

Framework de pruebas sobre aplicaciones web utilizando selenium webdriver

Tecnologias utilizadas:
- Lenguaje: Java - Maven
- Librerias:  Selenium - Cucumber
- Reporte: Allure

Configuración de ambiente:
1. Descargar y configurar variables de entorno de JAVA (JAVA_HOME y PATH): JDK_17
2. Para instalar Allure Report: <br>
   a. Descargar scoop <br>
   b. Instalar Allure: scoop install allure

Ejecutar pruebas:
- mvn clean test (basica)
- mvn clean test -Dambiente=test -Dcucumber.options="--tags '@XYZ' --tags '@High' (detallada)

Ejecutar reporte Allure desde la raiz de proyecto:
1. allure serve -h 127.0.0.1 -p 8087 --> -h 127.0.0.1 -p 8087 son opcionales
