package com.com.rubblesnask.core;


import com.rubblesnask.configuration.DemoConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Modulus on 11.03.2015.
 */
public class WailerApplication extends Application<DemoConfiguration> {
    @Override
    public void run(DemoConfiguration demoConfiguration, Environment environment) throws Exception {

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
