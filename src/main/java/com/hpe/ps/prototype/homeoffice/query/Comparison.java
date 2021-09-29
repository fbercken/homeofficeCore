package com.hpe.ps.prototype.homeoffice.query;

import java.util.Optional;

public enum Comparison {
    LESS_THAN,
	LESS_THAN_OR_EQUAL,
	EQUAL,
	NOT_EQUAL,
	GREATER_THAN,
	GREATER_THAN_OR_EQUAL;
	
	public static Optional<Comparison> forString(String value) {
	    
		if ( value == null) { return Optional.empty(); }
        
	    switch ( value.toLowerCase() ) {
            case "lt": case "<": case "less_than": 
                return Optional.of( Comparison.LESS_THAN );
            case "le": case "<=": case "less_than_or_equal": 
                return Optional.of( Comparison.LESS_THAN_OR_EQUAL );
            case "eq": case "==": case "equal": 
                return Optional.of( Comparison.EQUAL );
            case "ne": case "!=": case "not_equal": 
                return Optional.of( Comparison.NOT_EQUAL );
            case "gt": case ">": case "greater_than": 
               return Optional.of( Comparison.GREATER_THAN );
            case "ge": case ">=": case "greater_than_or_equal": 
               return Optional.of( Comparison.GREATER_THAN_OR_EQUAL );
            default: 
               return Optional.empty();
          }
     }
}
