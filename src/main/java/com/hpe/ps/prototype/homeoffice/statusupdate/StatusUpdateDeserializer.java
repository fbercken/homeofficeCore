package com.hpe.ps.prototype.homeoffice.statusupdate;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class StatusUpdateDeserializer implements Deserializer<StatusUpdateMessage> {
	
	 private final ObjectMapper objectMapper = new ObjectMapper();

	  public StatusUpdateDeserializer() {
		  
	  }

	  @Override
	  public void configure(Map<String, ?> props, boolean isKey) {
	  }

	  @Override
	  public StatusUpdateMessage deserialize(String topic, byte[] bytes) {
	    try {
	      
	    	if ( bytes == null || bytes.length == 0) {
	    	  
	            return null;
	        
	        } 
	        else {
	        
	            return objectMapper.readValue(bytes, StatusUpdateMessage.class);
	      
	        }
	    } 
	    catch (Exception e) {
	    	
	      throw new SerializationException(e);
	    
	    }
	  }

	  @Override
	  public void close() {
		  
	  }

}
