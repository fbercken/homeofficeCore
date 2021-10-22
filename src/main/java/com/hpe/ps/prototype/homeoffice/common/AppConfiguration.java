package com.hpe.ps.prototype.homeoffice.common;
/* vim:set ft=java ts=2 et sw=2 colorcolumn=80: */

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Application Configuration stored in HDFS.
 * <p>
 * This class obtains and parses a JSON object stored in the same cluster as
 * the application, containing configuration items used by the program.
 */

public class AppConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    private JSONObject jsonObject = new JSONObject();
    private final org.apache.hadoop.fs.Path hdfsConfigFilePath;

    public AppConfiguration(Path configFilePath) throws Exception {
        if (!configFilePath.isAbsolute()) {
            log.error("Config file '%s' must be specified with an absolute path", configFilePath.toString());
            throw new IllegalArgumentException(
                    String.format("config file '%s' must be specified with an absolute path", configFilePath.toString()));
        }

        String hadoop_version = Files.readString(Paths.get("/opt/mapr/hadoop/hadoopversion"));

        org.apache.hadoop.fs.Path core_site_path = new org.apache.hadoop.fs.Path(
                String.format("file:///opt/mapr/hadoop/hadoop-%s/etc/hadoop/core-site.xml",
                        hadoop_version));

        org.apache.hadoop.fs.Path hdfs_site_path = new org.apache.hadoop.fs.Path(
                String.format("file:///opt/mapr/hadoop/hadoop-%s/etc/hadoop/hdfs-site.xml",
                        hadoop_version));

        log.debug("core path = " + core_site_path.toString());
        log.debug("hdfs path = " + hdfs_site_path.toString());

        Configuration conf = new Configuration();

        conf.addResource(core_site_path);
        conf.addResource(hdfs_site_path);

        FileSystem hdfsFileSystem = FileSystem.get(URI.create("maprfs:///"), conf);

        hdfsConfigFilePath = new org.apache.hadoop.fs.Path(configFilePath.toString());

        FSDataInputStream in_stream = hdfsFileSystem.open(hdfsConfigFilePath);
        StringBuilder buffer = new StringBuilder();
        int read = 0;
        while ((read = in_stream.read()) != -1) {
            buffer.append((char) read);
        }

        JSONParser parser = new JSONParser();
        jsonObject = (JSONObject) parser.parse(buffer.toString());
    }

    /**
     * For the given configKey returns the value. If the key itself is not present or if the key does not contain a
     * non-empty value, throws IllegalArgumentException with a relevant message.
     * @param configKey
     * @return
     */
    public String getConfigValue(String configKey) {
        if (!jsonObject.containsKey(configKey)) {
            throw new IllegalArgumentException(
                    String.format("config object at maprfs://%s lacks %s entry", hdfsConfigFilePath, configKey));
        }
        String value = (String) jsonObject.get(configKey);
        if(value == null || value.trim().length() == 0){
            throw new IllegalArgumentException(
                    String.format("config object at maprfs://%s lacks a non-empty value for key %s", hdfsConfigFilePath, configKey));
        } else {
            return value.trim();
        }
    }
}
