/*
 * This file is generated by jOOQ.
 */
package com.cta4j.jooq;


import com.cta4j.jooq.tables.Station;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Cta4j extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>cta4j</code>
     */
    public static final Cta4j CTA4J = new Cta4j();

    /**
     * The table <code>cta4j.station</code>.
     */
    public final Station STATION = Station.STATION;

    /**
     * No further instances allowed
     */
    private Cta4j() {
        super("cta4j", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Station.STATION
        );
    }
}
