package com.hpe.ps.prototype.homeoffice.serializer;

import com.hpe.ps.prototype.homeoffice.statusupdate.StatusUpdateMessage;

public class StatusUpdateDeserializer extends JsonDeserializer<StatusUpdateMessage> {
	
	public StatusUpdateDeserializer() {
		
		super(StatusUpdateMessage.class);
	}
	
}
