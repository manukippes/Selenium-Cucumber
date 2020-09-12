# Selenium-Cucumber-SpringBoot

Framework de pruebas sobre aplicaciones web utilizando selenium webdriver

Tecnologias utilizadas:
- Lenguaje: Java - Maven
- Librerias: SpringBoot - Selenium - Cucumber
- Reporte: Allure - Cucumber-reporting
- IDE: Eclipse
- SO: Windows 10

Configuración de ambiente:
1. Descargar y configurar variables de entorno de JAVA (JAVA_HOME y PATH): JDK_1.8.0
2. Para instalar Allure Report: <br>
   a. Descargar scoop <br>
   b. Instalar Allure: scoop install allure

Ejecutar pruebas:
1. mvn clean test
o
1. mvn clean test -Dambiente=test -Dcucumber.options="--tags '@DetalleProducto or @ListadoProductos' --tags '@High'

Reporte Cucumber-reporting:
1. \target\cucumber-html-reports\overview-features.html

Ejecutar reporte Allure desde la raiz de proyecto:
1. allure serve -h 127.0.0.1 -p 8087 --> -h 127.0.0.1 -p 8087 son opcionales
