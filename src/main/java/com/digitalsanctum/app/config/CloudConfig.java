package com.digitalsanctum.app.config;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudConfig {

    @Bean
    public CloudEnvironment cloudEnvironment() {
        return new CloudEnvironment();
    }
}
