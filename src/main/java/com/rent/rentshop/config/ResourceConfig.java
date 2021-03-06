package com.rent.rentshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@CrossOrigin
public class ResourceConfig implements WebMvcConfigurer {

    @Value(value = "${file.dir.product}")
    private String fileDir;

    @Value(value = "${file.dir2.user}")
    private String fileDir2;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/img/products/**")
                .addResourceLocations("file:///" + fileDir)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

        registry.addResourceHandler("/static/img/users/**")
                .addResourceLocations("file:///" + fileDir2)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

}
