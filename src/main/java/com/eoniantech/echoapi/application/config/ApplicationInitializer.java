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

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Implementation of {@link WebApplicationInitializer}. This initializer can
 * replace the {@code web.xml} file. If a {@code web.xml} file exists, those
 * definitions will be processed first, followed by Servlet Spec 3 annotated
 * classes, and then any definitions defined here.
 *
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationInitializer implements WebApplicationInitializer {

    public static final String ENVIRONMENT_PROPERTY 
            = "com.myco.env";

    private static final String MISSING_ENVIRONMENT_PROPERTY
            = "You must provide the environment name via the "
            + "com.myco.env JVM argument. E.g, "
            + "-Dcom.myco.env=local|dev|stage|prod";

    @Override
    public void onStartup(
            ServletContext context) 
            throws ServletException {

        // Ensure the envionment is set
        if (System.getProperty(ENVIRONMENT_PROPERTY) == null) { 
            throw new IllegalStateException(MISSING_ENVIRONMENT_PROPERTY);
        }

        // Create the Spring context.
        AnnotationConfigWebApplicationContext springContext
                = new AnnotationConfigWebApplicationContext(); 

        // Activate the apporpriate profile
        springContext
                .getEnvironment()
                .setActiveProfiles(
                        System.getProperty(
                                ENVIRONMENT_PROPERTY));

        // Specify the spring configuration entry point.  
        springContext
                .register(
                        ApplicationConfiguration.class); 

        // Register the context listener
        context.addListener(new ContextLoaderListener(springContext));

        // Add Spring MVC
        ServletRegistration.Dynamic mvcServlet
                = context.addServlet("SpringMvc", 
                        new DispatcherServlet(springContext));
        mvcServlet.addMapping("/");

        // Add the REST API Servlet, set the mapping and init params.
        ServletRegistration.Dynamic restServlet
                = context.addServlet("api",
                        ServletContainer.class.getName());
        restServlet
                .addMapping(
                        "/api/*"); 
        restServlet
                .setInitParameter(
                        "javax.ws.rs.Application",
                        RestConfiguration.class.getName()); 

        // Add the UTF8 CharacterEncodingFilter
        FilterRegistration.Dynamic characterEncodingFilter
                = context.addFilter(
                        "characterEncodingFilter",
                        CharacterEncodingFilter.class); 
        characterEncodingFilter.addMappingForUrlPatterns(
                null, 
                false, 
                "/*");
        characterEncodingFilter.setInitParameter(
                "encoding", 
                "UTF-8");
        characterEncodingFilter.setInitParameter(
                "forceEncoding", 
                "true");

        // *Hack* This is needed. Jersey will think a Spring Context 
        // listener was not registered unless this param is set.
        context.setInitParameter("contextConfigLocation", "IS_NOT_NULL");
    }
}
