package ar.com.manutesting;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ar.com.manutesting.utiles.configuracionSpring.TestConfig;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j;

@SpringBootTest
@CucumberContextConfiguration
@ContextConfiguration(classes = {TestConfig.class, StartApplication.class}, loader = SpringBootContextLoader.class)
@Log4j
public class CucumberSpringContextConfiguration {
	@Before
	public void inicializarContexto() {
		log.info("Se inicializo correctamente el contexto de spring");
	}
}
