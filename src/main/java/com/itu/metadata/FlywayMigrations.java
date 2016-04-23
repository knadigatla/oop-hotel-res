package com.itu.metadata;

import org.flywaydb.core.Flyway;

public class FlywayMigrations {
    private Flyway flyway;


    public FlywayMigrations(FlywayDataSource dataSource) {
        this.flyway = new Flyway();
        this.flyway.setDataSource(dataSource.getDataSource());
        migrate();
    }

    public int migrate(){

        return this.flyway.migrate();
    }
}
