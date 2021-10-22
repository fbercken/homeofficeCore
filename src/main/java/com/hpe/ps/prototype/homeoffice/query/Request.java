package com.hpe.ps.prototype.homeoffice.query;
/* vim:set ft=java ts=2 sw=2 et colorcolumn=80: */

import com.hpe.ps.prototype.homeoffice.query.*;
import com.hpe.ps.prototype.homeoffice.common.*;

import java.util.List;

public class Request {

    private String id;

    private Table table;

    private List<Filter> filters;

    public Request() {
    }

    public Request(String id, Table table, List<Filter> filters) {
        this.id = id;
        this.table = table;
        this.filters = filters;
    }

    public String getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public void setTable( Table table ) {
        this.table = table;
    }

    public void setFilters( List<Filter> filters ) {
        this.filters = filters;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Result(");
        builder.append(" id = ").append(id);
        builder.append(", table = ").append(table);
        builder.append(", fields = [");
        boolean first = true;
        for (Filter filter : filters) {
            builder.append( (first) ? " " : ", " );
            first = false;
            builder.append( filter.toString() );
        }
        builder.append(" ]");
        builder.append(" )");
        return builder.toString();
    }

}