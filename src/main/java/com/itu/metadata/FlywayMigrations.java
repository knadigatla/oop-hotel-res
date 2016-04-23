package com.itu.metadata;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

public class FlywayMigrations {
    private Flyway flyway;

    final static Logger LOG = Logger.getLogger(FlywayMigrations.class);


    public FlywayMigrations(FlywayDataSource dataSource) {
        this.flyway = new Flyway();
        this.flyway.setDataSource(dataSource.getDataSource());
        migrate();
    }

    public int migrate(){

        return this.flyway.migrate();
    }
}
