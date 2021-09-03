package br.com.hereos.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.com.hereos.dto.response.HeroResponseDTO;

@Component
public class HeroRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
	    from("direct:getAllHereos")
	    	.doTry()
		    	.routeId("service-list-hereos")
			  //.log(LoggingLevel.INFO,">>> ${body}")
		    	.log(LoggingLevel.INFO, "Listando herois" )
		    	//.transform()
		    	//.simple("Hero cadastrado com sucesso");
		    	.to("http:{{hero.api.path}}?bridgeEndpoint=true")
		    	.unmarshal().json(JsonLibrary.Jackson, HeroResponseDTO[].class)
		    	.process(new Processor() {
		            public void process(Exchange exchange) throws Exception {
		            	//System.out.println(exchange.getIn().getBody());
		            	HeroResponseDTO[] payload = exchange.getIn().getBody(HeroResponseDTO[].class);
		            	
		            	for(HeroResponseDTO hero : payload) {
		            		System.out.println(hero);
		            	}
		            	
		                exchange.getIn().setBody(payload);
		           }})
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
	     	//.unmarshal().json(JsonLibrary.Jackson);
	    
	    from("direct:getHero")
	    	.doTry()
	        .routeId("service-get-hero")
	    	.setProperty("heroId", simple("${exchangeProperty.heroId}"))
		    .log("pesquisando heroi de id: ${header.heroId}")
//		    //.setHeader(Exchange.HTTP_QUERY, simple("heroId=${header.heroId}") )
//		    //.to("mock:http/${header.heroId}")
		    .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
		    //.log("${in.headers}")
		    .to("http:{{hero.api.path}}?bridgeEndpoint=true")
		    .unmarshal().json(JsonLibrary.Jackson, HeroResponseDTO.class)
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
	    
	    from("direct:postHero")
	    .doTry()
	    	.routeId("service-create-hero")
	    	.marshal().json()
	    	.log("direct:postHero: ${body}")
	    	.to("http:{{hero.api.path}}?bridgeEndpoint=true")
	    	.unmarshal().json(JsonLibrary.Jackson, Object.class)
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
