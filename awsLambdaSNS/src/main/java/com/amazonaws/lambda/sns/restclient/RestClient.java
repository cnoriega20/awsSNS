package com.amazonaws.lambda.sns.restclient;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.MessageAttribute;

public class RestClient {
	
	private Client client = ClientBuilder.newClient();
	
	public Response getResponse(Map<String, MessageAttribute> attributes, Context context) {
		//Logging
		
		
        context.getLogger().log("Inside rest client methods getResponse" );
		
		String resourcePath = attributes.get("ResourcePath").getValue();
		context.getLogger().log("Resource Path " + resourcePath);
        
		String pathVariable = attributes.get("PathVariable").getValue();
		context.getLogger().log("Id value: " + pathVariable );
		
		String REST_URI = resourcePath + "/" + pathVariable;
		
		context.getLogger().log("REST_URI: " + REST_URI );
		
		context.getLogger().log("Response status code: " + client.target(REST_URI).request(MediaType.APPLICATION_JSON).get().getStatus());
		
		context.getLogger().log("Response body: " + client.target(REST_URI).request(MediaType.APPLICATION_JSON).get().readEntity(String.class));
				
        return client.target(REST_URI).request(MediaType.APPLICATION_JSON).get();
        		
    }
	
}
