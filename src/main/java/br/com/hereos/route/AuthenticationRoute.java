package br.com.hereos.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("direct:postAuth")
	    .doTry()
	    	.routeId("service-hereos-auth")
	    	.marshal().json()
	    	.log("direct:postAuth: ${body}")
	    	.to("http:{{hero.api.path}}?bridgeEndpoint=true")
	    	.unmarshal().json(JsonLibrary.Jackson, Object.class)
	    	.log("return: ${body}")
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
			.doCatch(Exception.class)
	        .process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	                final Throwable ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
	                exchange.getIn().setBody(ex.getMessage());
	            }
	        })
	    .end();	  
		
	}
	

}
