package com.toomuch2learn.reactive.nativebuild.catalogue.crud.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadLocation;
}
