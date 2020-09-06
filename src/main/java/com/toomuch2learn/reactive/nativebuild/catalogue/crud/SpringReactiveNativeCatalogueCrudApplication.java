package com.toomuch2learn.reactive.nativebuild.catalogue.crud;

import com.toomuch2learn.reactive.nativebuild.catalogue.crud.configuration.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(proxyBeanMethods=false)
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class SpringReactiveNativeCatalogueCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveNativeCatalogueCrudApplication.class, args);
	}

}
