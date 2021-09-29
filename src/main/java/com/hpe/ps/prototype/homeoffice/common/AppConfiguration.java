package com.hpe.ps.prototype.homeoffice.common;


import java.net.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.hadoop.fs.FSDataInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


import net.minidev.json.parser.JSONParser;
import net.minidev.json.*;

/**
 * The Application Configuration stored in HDFS.
 *
 * This class obtains and parses a JSON object stored in the same cluster as
 * the application, containing configuration items used by the program.
 */

public class AppConfiguration {
	 
	private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);
	
	 private final String statusUpdateTopic;
	 
     public AppConfiguration(Path config) throws Exception {
		  
	    if (!config.isAbsolute()) {
	    	
	        log.error("Config file '%s' must be specified with an absolute path", config.toString());
	        
	    	throw new IllegalArgumentException(
	        
	          String.format("config file '%s' must be specified with an absolute path", config.toString() ) );
	    }

	    String hadoop_version = Files.readString( Paths.get( "/opt/mapr/hadoop/hadoopversion" ) );

	    org.apache.hadoop.fs.Path core_config = new org.apache.hadoop.fs.Path(
	      String.format("file:///opt/mapr/hadoop/hadoop-%s/etc/hadoop/core-site.xml",
	        hadoop_version) );
	 
	    org.apache.hadoop.fs.Path hdfs_config = new org.apache.hadoop.fs.Path(
	      String.format("file:///opt/mapr/hadoop/hadoop-%s/etc/hadoop/hdfs-site.xml",
	        hadoop_version) );
	 
	    
	    Configuration conf = new Configuration();
	    
	    conf.addResource(core_config);
	    conf.addResource(hdfs_config);

	    FileSystem file_system = FileSystem.get(URI.create( "maprfs:///"), conf );

	    org.apache.hadoop.fs.Path app_config = new org.apache.hadoop.fs.Path(
	      config.toString() );

	    FSDataInputStream in_stream = file_system.open(app_config);
	    StringBuilder buffer = new StringBuilder();
	    int read = 0;
	    
	    while ((read = in_stream.read() ) != -1 ) {
	    	
	      buffer.append( (char)read );
	      
	    }

	    JSONParser parser = new JSONParser();
	    
	    JSONObject jsonObject = (JSONObject)parser.parse( buffer.toString() );

        if (!jsonObject.containsKey("statusupdate_topic")) {
	    	
	        throw new IllegalArgumentException(
        	    	  String.format("config object at maprfs://%s lacks statusupdate_topic entry", app_config ) );
	    
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
