package com.digitalsanctum.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("default")
@PropertySource("file:${user.home}/.app/app.properties")
public class DefaultConfig {
}
