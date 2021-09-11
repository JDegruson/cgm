
package cgm.testdev;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource("datasource.xml")
public class LaunchApplication {

    private static final Logger logger = LogManager.getLogger(LaunchApplication.class);

	public static void main(String[] args) throws Exception {
		logger.info("Start appli");
		logger.info("Info level log message");
		logger.debug("Debug level log message");
		logger.error("Error level log message");
		SpringApplication.run(LaunchApplication.class, args);
	}


}
