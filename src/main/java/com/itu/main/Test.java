package com.itu.main;

import com.itu.metadata.FlywayDataSourceImpl;
import com.itu.metadata.FlywayMigrations;

import java.beans.PropertyVetoException;


public class Test {

    public static void main(String[] args) {

        try {
            FlywayMigrations fm = new FlywayMigrations(new FlywayDataSourceImpl());

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
