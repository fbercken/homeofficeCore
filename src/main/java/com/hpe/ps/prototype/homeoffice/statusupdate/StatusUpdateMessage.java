package com.hpe.ps.prototype.homeoffice.statusupdate;

import java.time.Instant;

public class StatusUpdateMessage {
	
	private String queryId;
	private String time;
	public Status status;
	private String clusterName;
	
	public StatusUpdateMessage () {

		this.queryId = "";
		this.time = "";
		this.status = Status.result_unknown;
		this.clusterName = "";

	}
	
	public StatusUpdateMessage (String queryId, Status status, String clusterName) {

		this.queryId = queryId;
		this.time = Instant.now().toString();
		this.status = status;
		this.clusterName = clusterName;

	}
	
	public StatusUpdateMessage (String queryId, Status status) {

		this.queryId = queryId;
		this.time = Instant.now().toString();
		this.status = status;
	}
	
	public StatusUpdateMessage (String queryId, String time, Status status, String cluster) {

		this.queryId = queryId;
		this.time = time;
		this.status = status;
		this.clusterName = cluster;

	}
	
	public StatusUpdateMessage (Status status, String cluster) {

		this.queryId = "";
		this.time = Instant.now().toString();
		this.status = status;
		this.clusterName = cluster;

	}
	 
	public String getQueryId() {

	    return queryId;

	}
	 
	public void setQueryId(String queryId) {

	    this.queryId = queryId;

	}

	public String getTime() {

	    return time;

	}

	public void setTime(String time) {

	    this.time = time;

	}

	public Status getStatus() {

	    return status;

	}

	public void setStatus(Status status) {

	    this.status = status;

	}


	public String getCluster() {

	    return clusterName;

	}

	public void setCluster(String clusterName) {

	    this.clusterName = clusterName;

	}
	
	public enum Status {  

	   query_started,

	   query_completed,

	   query_request_received,

	   sub_query_sent,

	   sub_query_send_failed,

	   sub_query_cancel,

	   sub_query_received,

	   sub_query_started,

	   sub_query_failed,

	   sub_query_completed,
		   
	   result_move_started,

	   result_move_failed,
	   
	   result_move_completed,

	   result_receive_started,

	   result_receive_failed,

	   result_receive_completed,
	   
	   result_unknown

	}
	
}
