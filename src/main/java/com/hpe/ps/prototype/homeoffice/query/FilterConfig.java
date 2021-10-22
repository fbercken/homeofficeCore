package com.hpe.ps.prototype.homeoffice.query;

import com.hpe.ps.prototype.homeoffice.query.*;
/**
 * A class that encapsulates the configuration for one filter.
 */
public class FilterConfig {

    private final Table table;

    private final Field field;

    private final Comparison comparison;

    private final String value;

    public FilterConfig(String table, String field, String comparison, String value) {
        this.table = Table.forString(table).orElseThrow(() -> {
            return new IllegalArgumentException(String.format( "table %s is not valid", table ) ); });
        this.field = Field.forString( this.table, field ).orElseThrow(() -> {
            return new IllegalArgumentException(String.format( "field %s for table %s is not valid", field, table ) ); });
        this.comparison = Comparison.forString(comparison).orElseThrow(() -> {
            return new IllegalArgumentException(String.format( "comparison %s is not valid", comparison ) ); });
        this.value = value;
    }

    public Filter getFilter() {
        return new Filter(field, comparison, value);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("FilterConfig( ");
        buffer.append("record = ").append(table).append(", ");
        buffer.append("field = ").append(field).append(", ");
        buffer.append("comparison = ").append(comparison).append(", ");
        buffer.append("value = ").append(value);
        buffer.append(" )");
        return buffer.toString();
    }

    @Override
    public boolean equals(Object object) {
        if ( ! ( object instanceof FilterConfig ) ) {
            return false;
        }
        FilterConfig other = (FilterConfig) object;
        return table.equals(other.table)
                && field.equals(other.field)
                && comparison.equals(other.comparison)
                && value.equals(other.value);
    }

    @Override
    public int hashCode() {
        int hashcode = 37;
        hashcode = 37 * hashcode + table.hashCode();
        hashcode = 37 * hashcode + field.hashCode();
        hashcode = 37 * hashcode + comparison.hashCode();
        hashcode = 37 * hashcode + value.hashCode();
        return hashcode;
    }
}
