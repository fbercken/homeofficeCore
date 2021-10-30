package com.hpe.ps.prototype.homeoffice.statusupdate;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hpe.ps.prototype.homeoffice.common.AppConfiguration;
import com.hpe.ps.prototype.homeoffice.serializer.JsonSerializer;

/**
 * This class is used to send the message on the status for the process to the Status Update queue. 
 * 
 * @author pmorenas
 *
 */
public class StatusUpdateProducer {
	
	private static Logger log = LoggerFactory.getLogger(StatusUpdateProducer.class);

	// Declare a new producer.
    private Producer<String, StatusUpdateMessage> producer;
	
    private String statusUpdateTopic;
    
    public StatusUpdateProducer() {
    	
    	setConfiguration("/ipfix/statusupdate.conf");
    }
    
    public StatusUpdateProducer(Producer<String, StatusUpdateMessage> producer, String statusUpdateTopic) {
    	
    	this.producer = producer;
    	this.statusUpdateTopic = statusUpdateTopic;
    	
    }
    
    public StatusUpdateProducer(String statusUpdateTopic) {
    	
    	this.statusUpdateTopic = statusUpdateTopic;
    }
    

    public Future<RecordMetadata> send(String queryId, StatusUpdateMessage message) {
     
    	return send(queryId, message, true);
        
    }
    
    public Future<RecordMetadata> send(String queryId, StatusUpdateMessage message, boolean autoComplete) {
        
    	Future<RecordMetadata> recordMetadata = null;
    	
    	try {
    		
    		ProducerRecord<String, StatusUpdateMessage> record = new ProducerRecord<>(statusUpdateTopic, 
    				                                                                  queryId, message);
    		recordMetadata = producer.send(record);
    		
    	}
		catch (Exception e) {
			
			log.error("Error when sending the status update message: {}", e.getMessage());
			System.out.println("Error when sending the status update message:" + e.getMessage());
			
		}
    	finally {
    		
            if (autoComplete) {
            	
            	producer.close();
            
            }
	    }
    	
    	return recordMetadata;
        
    }
    
    public void sendStatusUpdate(StatusUpdateMessage message) {
		
    	try {
			
		    configureProducer();
		    
		    send(message.getQueryId(), message);
			
			log.info("Sent message to Status Update Queue - Query ID: {}, Status: {}", message.getQueryId(), message.getStatus());
			
		}
		catch (Exception e) {
			
			log.error("Error when sending the status update message: {}", e.getMessage());
			
		}
		
	}
	
	
	private void configureProducer() {
    
		Properties props = new Properties();
		
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        		  "com.hpe.ps.prototype.homeoffice.serializer.JsonSerializer");
        
        producer = new KafkaProducer<>(props);

	}
	
    private void setConfiguration(String configPath) {
		try {
			Path config = Paths.get(configPath);
	    	AppConfiguration appConfiguration = new AppConfiguration(config);
	    	this.statusUpdateTopic = appConfiguration.getConfigValue("statusupdate_topic");
	    	log.info("Configuration parameters: {}", appConfiguration.toString());
		} catch (Exception e) {
			log.error("Error occurred: {}", e);
		}
	}
}