package br.com.hereos.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import br.com.hereos.dto.response.HeroResponseDTO;

@Component
public class HeroRouteConfig extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
//		errorHandler(
//			    deadLetterChannel("file:erro").
//			        logExhaustedMessageHistory(true).
//			        maximumRedeliveries(3).
//			            redeliveryDelay(5000).
//			        onRedelivery((Processor) new Processor() {            
//			            @Override
//			            public void process(Exchange exchange) throws Exception {
//			                int counter = (int) exchange.getIn().getHeader(Exchange.REDELIVERY_COUNTER);
//			                int max = (int) exchange.getIn().getHeader(Exchange.REDELIVERY_MAX_COUNTER);
//			                System.out.println("Redelivery - " + counter + "/" + max );
//			            }
//			        })
//			);
		
		restConfiguration()
	      .component("servlet")
	      .bindingMode(RestBindingMode.auto);
		 
		//REST ENDPOINT
		    rest("/hero")
		    	.description("CAMEL INTEGRATION HERO APIs")
		  		.skipBindingOnErrorCode(false) //Enable json marshalling for body in case of errors
	    	.get("/{heroId}")
	    		.id("get-hero")
	    		.consumes("application/json")
		  		//.produces(MediaType.APPLICATION_JSON.toString())
	  		  	.outType(HeroResponseDTO.class)
	    		.to("direct:getHero")  
	    	.get("")
	    	.id("get-all-heroeos")
	    		.consumes("application/json")
		  		//.produces(MediaType.APPLICATION_JSON.toString())
	  		  	.outType(HeroResponseDTO[].class)
	    		.to("direct:getAllHereos")   
	    	.post("")
    			.id("post-hero")
	    		.consumes("application/json")
	    		//.type(HeroRequestDTO.class)
	    		.outType(HeroResponseDTO.class)
		    	.to("direct:postHero");	
		    
		  //REST ENDPOINT
		    rest("/auth")
		    	.description("CAMEL INTEGRATION AUTHENTICATION APIs")
		  		.skipBindingOnErrorCode(false) //Enable json marshalling for body in case of errors
		  	.post("")
    			.id("post-auth")
	    		.consumes("application/json")
	    		//.type(HeroRequestDTO.class)
	    		.outType(Object.class)
		    	.to("direct:postAuth");
		
		
	}

}
