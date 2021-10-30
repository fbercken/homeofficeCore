package com.hpe.ps.prototype.homeoffice.query;
/* vim:set ft=java ts=2 sw=2 et colorcolumn=80: */

public class Filter {

    private Field field;

    private Comparison comparison;

    private String value;

    public Filter() {
    }

    public Filter(Field field, Comparison comparison, String value ) {
        this.field = field;
        this.value = value;
        this.comparison = comparison;
    }

    public Field getField() {
        return field;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public String getValue() {
        return value;
    }

    public void setField( Field field ) {
        this.field = field;
    }

    public void setComparison( Comparison comparison ) {
        this.comparison = comparison;
    }

    public void setValue( String value) {
        this.value = value;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Filter(");
        builder.append(" field = ").append(field);
        builder.append(", comparison = ").append(comparison);
        builder.append(", value = ").append(value);
        builder.append(" ]");
        builder.append(" )");
        return builder.toString();
    }
}