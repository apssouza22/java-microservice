package com.apssouza.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.turbine.TurbineProperties;
import org.springframework.context.annotation.Bean;

import com.netflix.turbine.discovery.ConfigPropertyBasedDiscovery;
import com.netflix.turbine.discovery.InstanceDiscovery;

@SpringBootApplication
@EnableTurbine
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
//	@Bean
//	public TurbineProperties turbineProperties() {
//		return new TurbineProperties();
//	}
//
//	@Bean
//	public InstanceDiscovery instanceDiscovery() {
//		return new ConfigPropertyBasedDiscovery();
//	}
    
}
