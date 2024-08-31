
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        plugin= {"pretty","json:target/cucumber.json", "junit:target/cucumber.xml"},
        monochrome=true,
        tags = "@landing"
)
public class RunnerTest {
//	@AfterClass
//	public static void generarReporte() {
//		File reportOutputDirectory = new File("target");
//		List<String> jsonFiles = new ArrayList<String>();
//		jsonFiles.add("target/cucumber.json");
//
//		String buildNumber = "1";
//		String projectName = "App Prueba";
//	}
}
