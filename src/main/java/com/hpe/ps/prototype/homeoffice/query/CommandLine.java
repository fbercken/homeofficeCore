package com.hpe.ps.prototype.homeoffice.query;

import java.util.*;
import java.nio.file.*;

/**
 * The command line passed to the program.
 *
 * This class accepts the args from the command line and
 * provides utilities to obtain their values.
 *
 */
public class CommandLine {

    /** The query id */
    private Optional<String> query_id;

    /** The path to the config file. */
    private Optional<Path> config_path;

    /** The filters to apply. */
    private ArrayList<FilterConfig> filter_configs;

    /**
     * Creates a new command line.
     *
     * The args passed to the command line will be parsed. Their
     * values will become available in the other methods.
     *
     * @param args The command line arguments.
     */
    public CommandLine(String[] args) {
        query_id = Optional.empty();
        config_path = Optional.empty();
        filter_configs = new ArrayList<>();

        for (String arg : args) {
            int equalsIndex = arg.indexOf('=');
            String argFlag = ( equalsIndex != -1 )
                    ? arg.substring(0, equalsIndex)
                    : arg;
            Optional<String> argValue = ( equalsIndex != -1 && equalsIndex + 1 < arg.length())
                    ? Optional.of( arg.substring( equalsIndex + 1 ) )
                    : Optional.empty();
            switch (argFlag) {
                case "--query":
                    query_id = argValue.flatMap(
                            (String x) -> { return Optional.of( x ); } );
                    break;
                case "--config":
                    config_path = argValue.flatMap(
                            (String x) -> { return Optional.of( Paths.get( x ) ); } );
                    break;
                case "--filter":
                    Optional<FilterConfig> config = argValue.flatMap(
                            (String x) -> {
                                String trimmed = ( x.endsWith(":") )
                                        ? x.substring( 0, x.length() - 1 )
                                        : x;
                                String[] fields = trimmed.split(":");
                                return ( fields.length == 4 )
                                        ? Optional.of( new FilterConfig( fields[0], fields[1], fields[2], fields[3] ) )
                                        : Optional.empty(); });
                    if ( config.isPresent() ) {
                        filter_configs.add( config.get() );
                    } else {
                        throw new IllegalArgumentException(
                                String.format( "Bad filter argument: '%s'\n", arg ) );
                    }
                    break;
                default:
                    throw new IllegalArgumentException(
                            String.format( "unknown argument: '%s'\n", arg ) );
            }
        }

    }

    /**
     * Retrieve the query id.
     *
     * @return The query identifier.
     */
    public Optional<String> getQueryId() {
        return query_id;
    }

    /**
     * Retrieve the config file path.
     *
     * @return The configuration file path.
     */
    public Optional<Path> getConfigPath() {
        return config_path;
    }

    /**
     * Retrieve the specified filters.
     *
     * @return The list of filter configs.
     */
    public List<FilterConfig> getFilterConfigs() {
        return filter_configs;
    }
}
