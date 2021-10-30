package com.hpe.ps.prototype.homeoffice.test.query;

import com.hpe.ps.prototype.homeoffice.query.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
public class FieldTest {

  private void testLookup( Field expected, Table table, String string ) {
    Assertions.assertEquals( 
        Optional.of( expected ), Field.forString( table, string ),
        String.format( "lookup of '%s' in '%s' returns %s", string, table, expected ) );
  }

  @Test
  public void test_lookup_ipfix_time() {
    testLookup( Field.IPFIX_TIME, Table.IPFIX, "time" );
    testLookup( Field.IPFIX_TIME, Table.IPFIX, "Time" );
    testLookup( Field.IPFIX_TIME, Table.IPFIX, "TIME" );
  }

  @Test
  public void test_lookup_ipfix_day_index() {
    testLookup( Field.IPFIX_DAY_INDEX, Table.IPFIX, "day_index" );
    testLookup( Field.IPFIX_DAY_INDEX, Table.IPFIX, "Day_Index" );
    testLookup( Field.IPFIX_DAY_INDEX, Table.IPFIX, "DAY_INDEX" );
  }

  @Test
  public void test_lookup_ipfix_type() {
    testLookup( Field.IPFIX_TYPE, Table.IPFIX, "type" );
    testLookup( Field.IPFIX_TYPE, Table.IPFIX, "Type" );
    testLookup( Field.IPFIX_TYPE, Table.IPFIX, "TYPE" );
  }

  @Test
  public void test_lookup_ipfix_flow_start_millis() {
    testLookup( Field.IPFIX_FLOW_START_MILLIS, Table.IPFIX, "start_ms" );
    testLookup( Field.IPFIX_FLOW_START_MILLIS, Table.IPFIX, "Start_Ms" );
    testLookup( Field.IPFIX_FLOW_START_MILLIS, Table.IPFIX, "Start_MS" );
    testLookup( Field.IPFIX_FLOW_START_MILLIS, Table.IPFIX, "START_MS" );
  }

  @Test
  public void test_lookup_ipfix_flow_end_millis() {
    testLookup( Field.IPFIX_FLOW_END_MILLIS, Table.IPFIX, "end_ms" );
    testLookup( Field.IPFIX_FLOW_END_MILLIS, Table.IPFIX, "End_Ms" );
    testLookup( Field.IPFIX_FLOW_END_MILLIS, Table.IPFIX, "End_MS" );
    testLookup( Field.IPFIX_FLOW_END_MILLIS, Table.IPFIX, "END_MS" );
  }

  @Test
  public void test_lookup_ipfix_octet_total_count() {
    testLookup( Field.IPFIX_OCTET_TOTAL_COUNT, Table.IPFIX, "octets" );
    testLookup( Field.IPFIX_OCTET_TOTAL_COUNT, Table.IPFIX, "Octets" );
    testLookup( Field.IPFIX_OCTET_TOTAL_COUNT, Table.IPFIX, "OCTETS" );
  }

  @Test
  public void test_lookup_ipfix_source_ipv4_address() {
    testLookup( Field.IPFIX_SOURCE_IPV4_ADDRESS, Table.IPFIX, "source_addr" );
    testLookup( Field.IPFIX_SOURCE_IPV4_ADDRESS, Table.IPFIX, "Source_Addr" );
    testLookup( Field.IPFIX_SOURCE_IPV4_ADDRESS, Table.IPFIX, "SOURCE_ADDR" );
  }

  @Test
  public void test_lookup_ipfix_dest_ipv4_address() {
    testLookup( Field.IPFIX_DESTINATION_IPV4_ADDRESS, Table.IPFIX, "dest_addr" );
    testLookup( Field.IPFIX_DESTINATION_IPV4_ADDRESS, Table.IPFIX, "Dest_Addr" );
    testLookup( Field.IPFIX_DESTINATION_IPV4_ADDRESS, Table.IPFIX, "DEST_ADDR" );
  }

  @Test
  public void test_lookup_ipfix_source_transport_port() {
    testLookup( Field.IPFIX_SOURCE_TRANSPORT_PORT, Table.IPFIX, "source_port" );
    testLookup( Field.IPFIX_SOURCE_TRANSPORT_PORT, Table.IPFIX, "Source_Port" );
    testLookup( Field.IPFIX_SOURCE_TRANSPORT_PORT, Table.IPFIX, "SOURCE_PORT" );
  }

  @Test
  public void test_lookup_ipfix_destination_transport_port() {
    testLookup( Field.IPFIX_DESTINATION_TRANSPORT_PORT, Table.IPFIX, "dest_port" );
    testLookup( Field.IPFIX_DESTINATION_TRANSPORT_PORT, Table.IPFIX, "Dest_Port" );
    testLookup( Field.IPFIX_DESTINATION_TRANSPORT_PORT, Table.IPFIX, "DEST_PORT" );
  }

  @Test
  public void test_lookup_ipfix_protocol_identifier() {
    testLookup( Field.IPFIX_PROTOCOL_IDENTIFIER, Table.IPFIX, "protocol_id" );
    testLookup( Field.IPFIX_PROTOCOL_IDENTIFIER, Table.IPFIX, "Protocol_Id" );
    testLookup( Field.IPFIX_PROTOCOL_IDENTIFIER, Table.IPFIX, "Protocol_ID" );
    testLookup( Field.IPFIX_PROTOCOL_IDENTIFIER, Table.IPFIX, "PROTOCOL_ID" );
  }

  @Test
  public void test_lookup_ipfix_flow_end_reason() {
    testLookup( Field.IPFIX_FLOW_END_REASON, Table.IPFIX, "end_reason" );
    testLookup( Field.IPFIX_FLOW_END_REASON, Table.IPFIX, "End_Reason" );
    testLookup( Field.IPFIX_FLOW_END_REASON, Table.IPFIX, "END_REASON" );
  }

  @Test
  public void test_lookup_ipfix_vlan_id() {
    testLookup( Field.IPFIX_VLAN_ID, Table.IPFIX, "vlan_id" );
    testLookup( Field.IPFIX_VLAN_ID, Table.IPFIX, "Vlan_Id" );
    testLookup( Field.IPFIX_VLAN_ID, Table.IPFIX, "VLan_Id" );
    testLookup( Field.IPFIX_VLAN_ID, Table.IPFIX, "Vlan_ID" );
    testLookup( Field.IPFIX_VLAN_ID, Table.IPFIX, "VLan_ID" );
    testLookup( Field.IPFIX_VLAN_ID, Table.IPFIX, "VLAN_ID" );
  }

  @Test
  public void test_lookup_ipfix_ip_class_of_service() {
    testLookup( Field.IPFIX_IP_CLASS_OF_SERVICE, Table.IPFIX, "service_class" );
    testLookup( Field.IPFIX_IP_CLASS_OF_SERVICE, Table.IPFIX, "Service_Class" );
    testLookup( Field.IPFIX_IP_CLASS_OF_SERVICE, Table.IPFIX, "SERVICE_CLASS" );
  }

  @Test
  public void test_lookup_of_null_table() {
    Assertions.assertEquals( 
        Optional.empty(), Field.forString( null, "time" ),
        String.format( "lookup of null returns %s", Optional.empty() ) );
  }

  @Test
  public void test_lookup_of_null_field() {
    Assertions.assertEquals( 
        Optional.empty(), Field.forString( Table.IPFIX, null ),
        String.format( "lookup of null returns %s", Optional.empty() ) );
  }

  @Test
  public void test_lookup_of_empty_string() {
    Assertions.assertEquals( 
        Optional.empty(), Field.forString( Table.IPFIX, "" ),
        String.format( "lookup of '' in 'IPFIX' returns %s", Optional.empty() ) );
  }

  @Test
  public void test_lookup_of_nonsense_value() {
    Assertions.assertEquals( 
        Optional.empty(), Field.forString( Table.IPFIX, "02h8nqpuvaineif" ),
        String.format( "lookup of '02h8nqpuvaineif' in 'IPFIX' returns %s", Optional.empty() ) );
  }

}