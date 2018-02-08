package com.paazl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.vibur.dbcp.ViburDBCPDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.paazl.data"})
@EntityScan(basePackages = {"com.paazl.data"})
public class DataConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        try {
            Class.forName(env.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ViburDBCPDataSource ds = new ViburDBCPDataSource();
        ds.setJdbcUrl(env.getProperty("db.jdbcUrl"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        ds.setPoolInitialSize(env.getProperty("db.poolInitialSize", Integer.class));
        ds.setPoolMaxSize(env.getProperty("db.poolMaxSize", Integer.class));
        ds.setConnectionIdleLimitInSeconds(env.getProperty("db.connectionIdleLimitInSeconds", Integer.class));
        ds.setTestConnectionQuery(env.getProperty("db.testConnectionQuery"));
        ds.setLogQueryExecutionLongerThanMs(env.getProperty("db.logQueryExecutionLongerThanMs", Integer.class));
        ds.setLogStackTraceForLongQueryExecution(env.getProperty("db.logStackTraceForLongQueryExecution", Boolean.class));
        ds.setStatementCacheMaxSize(env.getProperty("db.statementCacheMaxSize", Integer.class));
        ds.start();
        return ds;
    }
}