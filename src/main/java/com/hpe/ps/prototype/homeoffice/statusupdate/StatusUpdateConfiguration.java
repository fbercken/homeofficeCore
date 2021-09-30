package com.hpe.ps.prototype.homeoffice.statusupdate;

import com.hpe.ps.prototype.homeoffice.common.AppConfiguration;

import java.nio.file.Path;

import net.minidev.json.JSONObject;

/**
 * This class inherits from the AppConfiguration class that retrieves the configuration stored in HDFS.
 */

public class StatusUpdateConfiguration extends AppConfiguration {
	 
	private String statusUpdateTopic;
	
	
	public StatusUpdateConfiguration(Path config) throws Exception { 
		
		super(config);
	}
	 

    public void setConfig(JSONObject jsonObject, String appConfig) {
    	
	    if (!jsonObject.containsKey("statusupdate_topic")) {
		    	
		    throw new IllegalArgumentException(
	           	  String.format("config object at maprfs://%s lacks statusupdate_topic entry", appConfig ) );
		    
		} 
	    else {
	    	
	      this.statusUpdateTopic = (String)jsonObject.get("statusupdate_topic");
	    }
    	
    }
    
     public String getStatusUpdateTopic() {
		  
		  return statusUpdateTopic;
	  }
        
     
	  public String toString() {
	    
		StringBuilder builder = new StringBuilder();
	    
		builder.append("AppConfig(");
	    builder.append(" statusupdate_topic = ").append(statusUpdateTopic);
	    builder.append(" )");
	    
	    return builder.toString();
	  }
	
}