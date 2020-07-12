package com.projects.storage.DriveCloudStorage;

import com.projects.storage.DriveCloudStorage.config.StorageProperties;
import com.projects.storage.DriveCloudStorage.services.interfaces.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DriveCloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriveCloudStorageApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
