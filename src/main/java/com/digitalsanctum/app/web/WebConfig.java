package com.digitalsanctum.app.web;

import com.digitalsanctum.app.config.CloudConfig;
import com.digitalsanctum.app.config.DefaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@Import({DefaultConfig.class, CloudConfig.class})
@ComponentScan(basePackages = {"com.digitalsanctum.app.web.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Autowired
    public Environment env;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
    }

    private StringHttpMessageConverter stringConverter() {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
        return stringConverter;
    }

}

