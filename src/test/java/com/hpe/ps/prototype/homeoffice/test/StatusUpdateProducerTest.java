package com.hpe.ps.prototype.homeoffice.test;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;

import com.hpe.ps.prototype.homeoffice.statusupdate.EvenOddPartitioner;
import com.hpe.ps.prototype.homeoffice.statusupdate.StatusUpdateProducer;
import com.hpe.ps.prototype.homeoffice.statusupdate.StatusUpdateMessage;
import com.hpe.ps.prototype.homeoffice.statusupdate.StatusUpdateSerializer;
import com.hpe.ps.prototype.homeoffice.statusupdate.StatusUpdateMessage.Status;

import static java.util.Collections.emptySet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

public class StatusUpdateProducerTest {

    private final String TOPIC_NAME = "/ipfix/queries:statusUpdates";
    private final String QUERY_ID = "100";
    private final String CLUSTER_NAME = "edge1.homeoffice.poc";
    
    private MockProducer<String, StatusUpdateMessage> mockProducer;
    
    private StatusUpdateProducer statusUpdateProducer;
    
    private void buildMockProducer(boolean autoComplete) {
       
	    this.mockProducer = new MockProducer<>(autoComplete, new StringSerializer(),
				 							   new StatusUpdateSerializer());
    }
    
    
    @Test
    void testKeyValue_verifyHistory() throws ExecutionException, InterruptedException {

        buildMockProducer(true);

        statusUpdateProducer = new StatusUpdateProducer(mockProducer, TOPIC_NAME);
        
        Future<RecordMetadata> recordMetadataFuture = statusUpdateProducer.send(QUERY_ID, new StatusUpdateMessage(QUERY_ID,
       		 																		   Status.result_receive_completed, CLUSTER_NAME));
		
        assertTrue(recordMetadataFuture.isDone(), "Send is immediately completed");
        
        assertAll("history",
		         () -> assertTrue(mockProducer.history().size() == 1),
		         () -> assertTrue(mockProducer.history().get(0).key().equalsIgnoreCase("100")) 
		     );
		
		assertEquals(TOPIC_NAME, recordMetadataFuture.get().topic());
		
		assertAll("recordmetadata",
		         () -> assertEquals(0L, recordMetadataFuture.get().offset(), "Offset should be 0"),
		         () -> assertTrue(recordMetadataFuture.get().partition() == 0)
		     );
		
        mockProducer.clear();
        
        assertEquals(0, mockProducer.history().size(), "Clear should erase the history");
        
    }
    
    
    @Test
    void testgivenKeyValue_sendOnlyAfterFlush() {
    	
        buildMockProducer(false);
        
        statusUpdateProducer = new StatusUpdateProducer(mockProducer, TOPIC_NAME);
        
        Future<RecordMetadata> record = statusUpdateProducer.send(QUERY_ID, new StatusUpdateMessage(QUERY_ID,
					                                              Status.result_receive_completed, CLUSTER_NAME), false);
        
        assertFalse(record.isDone(), "Send will not be completed");
        
        
        mockProducer.flush();
       	
        assertTrue(record.isDone(), "Send will be completed");
        
        mockProducer.close();
       
    }
    
    @Test
    void testKeyValue_ReturnException() {
    	
        buildMockProducer(false);
        
        statusUpdateProducer = new StatusUpdateProducer(mockProducer, TOPIC_NAME);
        
        Future<RecordMetadata> record = statusUpdateProducer.send(QUERY_ID, new StatusUpdateMessage(QUERY_ID,
					                                              Status.result_receive_completed, CLUSTER_NAME));
        RuntimeException e = new RuntimeException();
        mockProducer.errorNext(e);

        try {
            
        	record.get();
        } 
        catch (ExecutionException | InterruptedException ex) {
        
        	assertEquals(e, ex.getCause());
        
        }
        assertTrue(record.isDone());
    }
    
    @Test
    void testgivenKeyValue_verifyPartitionNumber() throws ExecutionException, InterruptedException {

        PartitionInfo partitionInfo0 = new PartitionInfo(TOPIC_NAME, 0, null, null, null);
        
        PartitionInfo partitionInfo1 = new PartitionInfo(TOPIC_NAME, 1, null, null, null);
        
        List<PartitionInfo> list = new ArrayList<>();
        
        list.add(partitionInfo0);
        list.add(partitionInfo1);
       
        Cluster cluster = new Cluster("kafkab", new ArrayList<Node>(), list, emptySet(), emptySet());
        
        this.mockProducer = new MockProducer<>(cluster, true, new EvenOddPartitioner(),
							        		   new StringSerializer(), new StatusUpdateSerializer());
        
        statusUpdateProducer = new StatusUpdateProducer(mockProducer, TOPIC_NAME);
        
        Future<RecordMetadata> recordMetadataFuture = statusUpdateProducer.send("partition", new StatusUpdateMessage(QUERY_ID,
					                                                           Status.result_receive_completed, CLUSTER_NAME));
        
        assertTrue(recordMetadataFuture.get().partition() == 1);

    }
    
}
