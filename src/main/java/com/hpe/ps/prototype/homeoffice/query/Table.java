package com.hpe.ps.prototype.homeoffice.query;

import java.util.HashMap;
import java.util.Optional;


/**
 * A specific kind of record being loaded.
 *
 */
public enum Table {

  /**
	* The IPFIX table.
    */
    IPFIX( "ipfix" );

    /* A hashmap to lookup tables by their name */
    private static HashMap<String, Table> tables_by_name;

    static {
        tables_by_name = new HashMap<>();
        for ( Table table : Table.values() ) {
            tables_by_name.put( table.name, table );
        }
    }

   /**
     * Fetches a table by its name.
     *
     * This method returns the Table for a name.
     *
     * @param value The name of the table.
     * @return The table, if it exists.
     */
    public static Optional<Table> forString(String value) {
        if ( value == null ) { return Optional.empty(); }
            return Optional.ofNullable( tables_by_name.get( value.toLowerCase() ) );
    }


    /* The name of the table */
    private final String name;

    /**
      * Constructs a new table.
      *
      * Tables have names, which cannot be null or empty ("").
      *
      * @param name The name of the table.
      */
    private Table( String name ) {
        if ( name == null || name.equals("") ) {
            throw new IllegalArgumentException( "name is null or empty" ) ;
	    }
	    this.name = name;
	  }
}
