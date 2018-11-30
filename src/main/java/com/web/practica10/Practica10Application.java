package com.web.practica10;

import com.web.practica10.property.FileStorageProperties;
import com.web.practica10.service.UserDetailsServiceImp;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ComponentScan
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Practica10Application {
	public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Practica10Application.class, args);

        UserDetailsServiceImp userDetailsServiceImp;

        try {
            userDetailsServiceImp = (UserDetailsServiceImp) applicationContext.getBean("userDetailsServiceImp");
            userDetailsServiceImp.createAdmin();
        } catch (BeansException e) {
            e.printStackTrace();
        }
	}
}
