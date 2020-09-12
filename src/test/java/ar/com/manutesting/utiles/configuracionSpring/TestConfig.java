package ar.com.manutesting.utiles.configuracionSpring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "ar.com.manutesting")
@PropertySource("classpath:/configProperties/application-${ambiente:test}.properties")
public class TestConfig {

}
