package com.rgerwig.adserver;
import javax.json.stream.JsonGenerator;


import org.glassfish.jersey.server.ResourceConfig;


public class AdServerApplication extends ResourceConfig {
    /**
     * Constructor to register classes and packages
     */
    public AdServerApplication() {
        packages("com.rgerwig.adserver.service");
        property(JsonGenerator.PRETTY_PRINTING, true);
    }
}
