package com.rubblesnask.core;


import com.rubblesnask.api.WailResource;
import com.rubblesnask.configuration.DemoConfiguration;
import com.rubblesnask.jdbi.WailDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Modulus on 11.03.2015.
 */
public class WailerApplication extends Application<DemoConfiguration> {
    @Override
    public void run(DemoConfiguration demoConfiguration, Environment environment) throws Exception {
        final DBIFactory dbiFactory = new DBIFactory();
        final DBI jdbi = dbiFactory.build(environment, demoConfiguration.getDataSourceFactory(), "postgresql");
        final WailDAO dao = jdbi.onDemand(WailDAO.class);
        environment.jersey().register(new WailResource(dao));

    }

    public static void main(String[] args) throws Exception{
        new WailerApplication().run(args);
    }

    @Override
    public String getName() {
        return "wailer-application";
    }

    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }
}
