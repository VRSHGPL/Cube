package com.vg.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@ComponentScan("com.vg.*")
@CrossOrigin(origins = { "http://localhost:4200" })
public class CubeWebApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CubeWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CubeWebApplication.class, args);
	}

	@RequestMapping("/ping")
	public String ping() {
		LOGGER.debug("App's up");
		return "App's Up";
	}

	/*@Bean
	EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
		return (ConfigurableEmbeddedServletContainer container) -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
				tomcat.addConnectorCustomizers((connector) -> {
					connector.setMaxPostSize(10000000); // 10 MB
				
				});
			}
		};
	}
*/
}
/*
 * @RefreshScope
 * 
 * @RestController class MessageRestController {
 * 
 * @Value("${app.global.message}") private String message;
 * 
 * @RequestMapping("/message") String getMessage() { return this.message; } }
 */
