package com.hpe.ps.prototype.homeoffice.test.query;
/* vim:set ft=java ts=2 et sw=2 colorcolumn=80: */

import com.hpe.ps.prototype.homeoffice.query.Comparison;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ComparisonTest {

    private void testLookup( Comparison expected, String string ) {
        Assertions.assertEquals(
                Optional.of( expected ), Comparison.forString( string ),
                String.format( "lookup of '%s' returns %s", string, expected ) );
    }

    @Test
    public void test_lookup_less_than() {
        testLookup( Comparison.LESS_THAN, "less_than" );
        testLookup( Comparison.LESS_THAN, "Less_Than" );
        testLookup( Comparison.LESS_THAN, "LESS_THAN" );
    }

    @Test
    public void test_lookup_math_less_than() {
        testLookup( Comparison.LESS_THAN, "<" );
    }

    @Test
    public void test_lookup_lt() {
        testLookup( Comparison.LESS_THAN, "lt" );
    }

    @Test
    public void test_lookup_less_than_or_equal() {
        testLookup( Comparison.LESS_THAN_OR_EQUAL, "less_than_or_equal" );
        testLookup( Comparison.LESS_THAN_OR_EQUAL, "Less_Than_Or_Equal" );
        testLookup( Comparison.LESS_THAN_OR_EQUAL, "LESS_THAN_OR_EQUAL" );
    }

    @Test
    public void test_lookup_math_less_than_or_equal() {
        testLookup( Comparison.LESS_THAN_OR_EQUAL, "<=" );
    }

    @Test
    public void test_lookup_le() {
        testLookup( Comparison.LESS_THAN_OR_EQUAL, "le" );
    }

    @Test
    public void test_lookup_equal() {
        testLookup( Comparison.EQUAL, "equal" );
        testLookup( Comparison.EQUAL, "Equal" );
        testLookup( Comparison.EQUAL, "EQUAL" );
    }

    @Test
    public void test_lookup_math_equals() {
        testLookup( Comparison.EQUAL, "==" );
    }

    @Test
    public void test_lookup_eq() {
        testLookup( Comparison.EQUAL, "eq" );
    }

    @Test
    public void test_lookup_not_equal() {
        testLookup( Comparison.NOT_EQUAL, "not_equal" );
        testLookup( Comparison.NOT_EQUAL, "Not_Equal" );
        testLookup( Comparison.NOT_EQUAL, "NOT_EQUAL" );
    }

    @Test
    public void test_lookup_math_not_equal() {
        testLookup( Comparison.NOT_EQUAL, "!=" );
    }

    @Test
    public void test_lookup_ne() {
        testLookup( Comparison.NOT_EQUAL, "ne" );
    }

    @Test
    public void test_lookup_greater_than() {
        testLookup( Comparison.GREATER_THAN, "greater_than" );
        testLookup( Comparison.GREATER_THAN, "Greater_Than" );
        testLookup( Comparison.GREATER_THAN, "GREATER_THAN" );
    }

    @Test
    public void test_lookup_math_greater_than() {
        testLookup( Comparison.GREATER_THAN, ">" );
    }

    @Test
    public void test_lookup_gt() {
        testLookup( Comparison.GREATER_THAN, "gt" );
    }

    @Test
    public void test_lookup_greater_than_or_equal() {
        testLookup( Comparison.GREATER_THAN_OR_EQUAL, "greater_than_or_equal" );
        testLookup( Comparison.GREATER_THAN_OR_EQUAL, "Greater_Than_Or_Equal" );
        testLookup( Comparison.GREATER_THAN_OR_EQUAL, "GREATER_THAN_OR_EQUAL" );
    }

    @Test
    public void test_lookup_math_greater_than_or_equal() {
        testLookup( Comparison.GREATER_THAN_OR_EQUAL, ">=" );
    }

    @Test
    public void test_lookup_ge() {
        testLookup( Comparison.GREATER_THAN_OR_EQUAL, "ge" );
    }

    @Test
    public void test_lookup_of_null() {
        Assertions.assertEquals(
                Optional.empty(), Comparison.forString( null ),
                String.format( "lookup of null returns %s", Optional.empty() ) );
    }

    @Test
    public void test_lookup_of_empty_string() {
        Assertions.assertEquals(
                Optional.empty(), Comparison.forString( "" ),
                String.format( "lookup of '' returns %s", Optional.empty() ) );
    }

    @Test
    public void test_lookup_of_nonsense_value() {
        Assertions.assertEquals(
                Optional.empty(), Comparison.forString( "02h8nqpuvaineif" ),
                String.format( "lookup of '02h8nqpuvaineif' returns %s", Optional.empty() ) );
    }

}