package br.com.hereos;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="br.com.hereos")
public class HereosCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HereosCamelApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean<CamelHttpTransportServlet> camelServletRegistrationBean() {
    ServletRegistrationBean<CamelHttpTransportServlet> registration = new ServletRegistrationBean<CamelHttpTransportServlet>(new CamelHttpTransportServlet(), "/camel/*");
    registration.setName("CamelServlet");
    return registration;
  }

}
