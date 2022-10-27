package com.phptravels.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationManager {

    public static BrowserConfig getBrowserConfigInstance() {
        return ConfigCache.getOrCreate(BrowserConfig.class);
    }
}
