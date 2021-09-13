package com.hpe.ps.prototype.homeoffice.statusupdate;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;

public class StatusUpdateSerializer implements Serializer<StatusUpdateMessage> 
{
    private final ObjectMapper objectMapper;
  
    private static final Logger log = LoggerFactory.getLogger(StatusUpdateSerializer.class);

    public StatusUpdateSerializer() {
	  
        objectMapper = new ObjectMapper();

    }

    @Override
    public void configure( Map<String, ?> props, boolean isKey ) {
  
    }

    @Override
    public byte[] serialize( String topic, StatusUpdateMessage data ) {
  
        try {
    	  
    	    log.info("Sending: {}", new String(objectMapper.writeValueAsBytes(data)));
      
    	    return objectMapper.writeValueAsBytes(data);
      
         } 
         catch (JsonProcessingException e) {
        
    	     log.info("Error serializing data: {}", e);
    	   
    	     throw new IllegalArgumentException("Error serializing data: " + e.getMessage(), e);
	     }
     }

     @Override
     public void close() {
	  
     }
 
}
