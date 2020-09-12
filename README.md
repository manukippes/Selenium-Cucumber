# Selenium-Cucumber-SpringBoot

Framework de pruebas sobre API REST

Tecnologias utilizadas:

Lenguaje: Java - Maven
Librerias: SpringBoot - Cucumber
Reporte: Allure - Cucumber-reporting
IDE: Eclipse
SO: Windows 10
Configuración de ambiente:

Descargar y configurar variables de entorno de JAVA (JAVA_HOME y PATH): JDK_1.8.0
Para instalar Allure Report: a. Descargar scoop c. Instalar Allure: scoop install allure

Ejecutar pruebas:
mvn clean test
o
mvn clean test -Dambiente=test -Dcucumber.options="--tags '@DetalleProducto or @ListadoProductos' --tags '@High'

Reporte Cucumber-reporting:
\target\cucumber-html-reports\overview-features.html

Ejecutar reporte Allure desde la raiz de proyecto:
allure serve -h 127.0.0.1 -p 8087 --> -h 127.0.0.1 -p 8087 son opcionales
