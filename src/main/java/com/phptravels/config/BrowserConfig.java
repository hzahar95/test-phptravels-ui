package com.phptravels.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/browser-config.properties")
public interface BrowserConfig extends Config {

    @Key("targetBrowser")
    @DefaultValue("CHROME")
    String defaultBrowser();

    @Key("baseUrl")
    @DefaultValue("https://phptravels.org")
    String baseUrl();

    @Key("apiBaseUrl")
    @DefaultValue("https://phptravels.net/api/api/main/app")
    String apiBaseUrl();

}
