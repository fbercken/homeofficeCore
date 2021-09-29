package com.hpe.ps.prototype.homeoffice.serializer;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.hpe.ps.prototype.homeoffice.statusupdate.StatusUpdateMessage;

public final class CustomSerdes {

     public static final class StatusUpdateMessageSerde
            extends Serdes.WrapperSerde<StatusUpdateMessage> {
    	
        public StatusUpdateMessageSerde() {
        	
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(StatusUpdateMessage.class));
        }
    }

    public static Serde<StatusUpdateMessage> StatusUpdateMessage() {
    	
        return new CustomSerdes.StatusUpdateMessageSerde();
    }

}
