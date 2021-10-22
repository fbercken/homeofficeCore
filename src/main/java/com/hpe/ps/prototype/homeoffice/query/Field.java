package com.hpe.ps.prototype.homeoffice.query;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;

	/**
	 * Describes a Field in a Table.
	 *
	 */
	public enum Field {
	  IPFIX_TIME( Table.IPFIX, "time", "time" ),
	  IPFIX_DAY_INDEX( Table.IPFIX, "day_index", "dayindex" ),
	  IPFIX_TYPE( Table.IPFIX, "type", "type" ),
	  IPFIX_FLOW_START_MILLIS( Table.IPFIX, "start_ms", "flowStartMilliseconds" ),
	  IPFIX_FLOW_END_MILLIS( Table.IPFIX, "end_ms", "flowEndMilliseconds" ),
	  IPFIX_OCTET_TOTAL_COUNT( Table.IPFIX, "octets", "octetTotalCount" ),
	  IPFIX_SOURCE_IPV4_ADDRESS( Table.IPFIX, "source_addr", "sourceIPv4Address" ),
	  IPFIX_DESTINATION_IPV4_ADDRESS( Table.IPFIX, "dest_addr", "destinationIPv4Address" ),
	  IPFIX_SOURCE_TRANSPORT_PORT( Table.IPFIX, "source_port", "sourceTransportPort" ),
	  IPFIX_DESTINATION_TRANSPORT_PORT( Table.IPFIX, "dest_port", "destinationTransportPort" ),
	  IPFIX_PROTOCOL_IDENTIFIER( Table.IPFIX, "protocol_id", "protocolIdentifier" ),
	  IPFIX_FLOW_END_REASON( Table.IPFIX, "end_reason", "flowEndReason" ),
	  IPFIX_VLAN_ID( Table.IPFIX, "vlan_id", "vlanId" ),
	  IPFIX_IP_CLASS_OF_SERVICE( Table.IPFIX, "service_class", "ipClassOfService" );
	
	  private static EnumMap<Table, EnumSet<Field>> fields;
	
	  static {
	    fields = new EnumMap<>( Table.class );
	    for ( Field field : Field.values() ) {
	      fields.putIfAbsent( field.getTable(), EnumSet.noneOf( Field.class ) );
	      fields.get( field.getTable() ).add( field );
	    }
	  }
	
	  public static Optional<Field> forString( Table table, String value ) {
	    if ( table == null || value == null ) { return Optional.empty(); }
	    String lookup = value.toLowerCase();
	    return fields.get( table ).stream()
	      .filter( (field) -> field.name.equals(lookup) ).findFirst();
	  }
	
	  private final Table table;
	
	  private final String name;
	
	  private final String column;
	
	  private Field( Table table, String name, String column ) {
	    this.table = table;
	    this.name = name;
	    this.column = column;
	  }
	
	  public Table getTable() {
	    return table;
	  }
	
	  public String getName() {
	    return name;
	  }
	
	  public String getColumn() {
	    return column;
	  }
	
	  public Object parse( String value ) {
	    return value;
	  }
	
	}
	