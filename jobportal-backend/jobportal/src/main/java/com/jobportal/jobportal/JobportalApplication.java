package com.jobportal.jobportal;

import com.jobportal.jobportal.configs.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class JobportalApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobportalApplication.class, args);
	}
}
