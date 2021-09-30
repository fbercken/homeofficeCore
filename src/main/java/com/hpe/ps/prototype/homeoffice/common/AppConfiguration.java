package com.hpe.ps.prototype.homeoffice.common;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.net.URI;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.JSONObject;

/**
 * The Application Configuration stored in HDFS.
 *
 * This class obtains and parses a JSON object stored in the same cluster as
 * the application, containing configuration items used by the program.
 */

public abstract class AppConfiguration {
	 
    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);
    
	
    public AppConfiguration(java.nio.file.Path config) throws Exception {
     
	    if (!config.isAbsolute()) {
	    	
	        log.error("Config file '%s' must be specified with an absolute path", config.toString());
	        
	    	throw new IllegalArgumentException(
	        
	          String.format("config file '%s' must be specified with an absolute path", config.toString() ) );
	    }

	    String hadoop_version = Files.readString( Paths.get( "/opt/mapr/hadoop/hadoopversion" ) );

	     Path core_config = new Path(
	      String.format("file:///opt/mapr/hadoop/hadoop-%s/etc/hadoop/core-site.xml",
	        hadoop_version) );
	 
	    Path hdfs_config = new Path(
	      String.format("file:///opt/mapr/hadoop/hadoop-%s/etc/hadoop/hdfs-site.xml",
	        hadoop_version) );
	 
	    
	    Configuration conf = new Configuration();
	    
	    conf.addResource(core_config);
	    conf.addResource(hdfs_config);

	    FileSystem file_system = FileSystem.get(URI.create( "maprfs:///"), conf );

	    Path app_config = new Path(
	      config.toString() );

	    FSDataInputStream in_stream = file_system.open(app_config);
	    StringBuilder buffer = new StringBuilder();
	    int read = 0;
	    
	    while ((read = in_stream.read() ) != -1 ) {
	    	
	      buffer.append( (char)read );
	      
	    }

	    JSONParser parser = new JSONParser();
	    
	    JSONObject jsonObject = (JSONObject)parser.parse( buffer.toString() );

        setConfig(jsonObject, app_config.toString());
	    
   }
     
   public abstract void setConfig(JSONObject jsonObject, String appConfig);
   
    
}
