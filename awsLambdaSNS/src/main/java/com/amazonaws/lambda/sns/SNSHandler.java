package com.amazonaws.lambda.sns;

import java.util.Map;

import javax.ws.rs.core.Response;

import com.amazonaws.lambda.sns.restclient.RestClient;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.MessageAttribute;

public class SNSHandler implements RequestHandler<SNSEvent, String> {
	
	private RestClient restClient = new RestClient();
	
    
    public String handleRequest(SNSEvent event, Context context) {
        context.getLogger().log("Received event: " + event);
        String  stsMessage = event.getRecords().get(0).getSNS().getMessage();
        context.getLogger().log("From SNS: " + stsMessage);
        Map<String, MessageAttribute> attributes = event.getRecords().get(0).getSNS().getMessageAttributes();
        context.getLogger().log("Invoking RestClient from lambda... " );
        Response response = restClient.getResponse(attributes);        
        return response.toString();
    }
}
