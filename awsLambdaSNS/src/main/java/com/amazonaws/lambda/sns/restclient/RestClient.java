package com.amazonaws.lambda.sns.restclient;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amazonaws.services.lambda.runtime.events.SNSEvent.MessageAttribute;

public class RestClient {
	
	private Client client = ClientBuilder.newClient();
	
	public Response getResponse(Map<String, MessageAttribute> attributes) {
		String resourcePath = attributes.get("ResourcePath").getValue();
		String pathVariable = attributes.get("PathVariable").getValue();
		
		String REST_URI = resourcePath + "/" + pathVariable;
		
        return client.target(REST_URI).request(MediaType.APPLICATION_JSON).get();
        		
    }
	
}
