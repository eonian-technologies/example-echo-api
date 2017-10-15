/*
 * Copyright 2017 Eonian Technologies.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eoniantech.echoapi.application.config;

import com.eoniantech.echoapi.application.service.EchoService;
import com.eoniantech.echoapi.application.service.ServiceRegistry;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Root class for the application's Spring configuration. This class is
 * annotated with {@link ComponentScan} which will look for other classes in
 * this package with the {@link Configuration} annotation. It will not 
 * component scan for any other Spring stereotypes.
 *
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
@Configuration
@ComponentScan(
        basePackages = "com.myco.echoapi.application.config",
        useDefaultFilters = false,
        includeFilters = {
            @Filter(Configuration.class)
        })
@PropertySource(
        name = "buildProperties", 
        value = "classpath:build.properties", 
        ignoreResourceNotFound = true)
@PropertySource(
        name = "applicationProperties",
        value = "classpath:properties/application-${com.eonian.env}.properties", 
        ignoreResourceNotFound = true)
public class ApplicationConfiguration {

    @Bean
    @Profile("!test")
    public static PropertySourcesPlaceholderConfigurer pspc() {

        PropertySourcesPlaceholderConfigurer pspc
                = new PropertySourcesPlaceholderConfigurer();

        pspc.setLocalOverride(true);

        return pspc;
    }

    @Bean 
    public ServiceRegistry serviceRegistry() {
        return new ServiceRegistry();
    }

    @Bean
    public EchoService echoService() {
        return new EchoService();
    } 
}
