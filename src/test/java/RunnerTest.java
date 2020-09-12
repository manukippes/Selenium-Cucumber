
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.log4j.Log4j;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        tags = {"@DetalleProducto or @ListadoProductos","@High"},
        plugin= {"pretty","json:target/cucumber.json", "junit:target/cucumber.xml", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        monochrome=true,
        strict = true,
        dryRun = false
)

@Log4j
public class RunnerTest {
	@AfterClass
	public static void generarReporte() {
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("target/cucumber.json");

		String buildNumber = "1";
		String projectName = "App Prueba";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc for details
		//configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
	    configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
		// do not make scenario failed when step has status SKIPPED
		configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
		configuration.setBuildNumber(buildNumber);
		// addidtional metadata presented on main page
		configuration.addClassifications("Plataforma", "Windows");
		configuration.addClassifications("Navegador", "Chrome");
		configuration.setSortingMethod(SortingMethod.NATURAL);

		// optionally add metadata presented on main page via properties file
//		List<String> classificationFiles = new ArrayList<String>();
//		classificationFiles.add("properties-1.properties");
//		classificationFiles.add("properties-2.properties");
//		configuration.addClassificationFiles(classificationFiles);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
		log.info("Se generó el reporte de cucumber en formato html en la ruta: "+reportOutputDirectory.getAbsolutePath()+"\\cucumber-html-reports\\overview-features.html");
		// and here validate 'result' to decide what to do if report has failed
	}
}
