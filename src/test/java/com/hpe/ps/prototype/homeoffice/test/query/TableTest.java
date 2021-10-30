package com.hpe.ps.prototype.homeoffice.test.query;
/* vim:set ft=java ts=2 et sw=2 colorcolumn=80: */

import com.hpe.ps.prototype.homeoffice.query.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TableTest {


    @Test
    public void test_lookup_ipfix() {
        Assertions.assertEquals(
                Optional.of(Table.IPFIX), Table.forString("ipfix" ),
                String.format( "lookup of 'ipfix' returns %s", Table.IPFIX ) );
    }


    @Test
    public void test_lookup_of_null() {
        Assertions.assertEquals(
                Optional.empty(), Table.forString(null ),
                String.format( "lookup of '' returns %s", Optional.empty() ) );
    }

    @Test
    public void test_lookup_of_empty_string() {
        Assertions.assertEquals(
                Optional.empty(), Table.forString( "" ),
                String.format( "lookup of '' returns %s", Optional.empty() ) );
    }

    @Test
    public void test_lookup_of_nonsense_value() {
        Assertions.assertEquals(
                Optional.empty(), Table.forString( "02h8nqpuvaineif" ),
                String.format( "lookup of '02h8nqpuvaineif' returns %s", Optional.empty() ) );
    }
}