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

import java.util.Arrays;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring configuration for MVC. This class is annotated with 
 * {@link ComponentScan} which will look for classes in the 
 * {@code portadaptor/web} package annotated with the {@link Controller} 
 * annotation. It will not component scan for any other Spring stereotypes.
 * 
 * @author Michael Andrews | Michael.Andrews@eoniantech.com
 * @since 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "com.eoniantech.echoapi.portadaptor.web",
        useDefaultFilters = false,
        includeFilters = {
            @Filter(Controller.class)})
public class MvcConfiguration implements WebMvcConfigurer {

    private final static String CSS_PATH = "/WEB-INF/statics/css/";
    private final static String JS_PATH = "/WEB-INF/statics/js/";
    private final static String IMG_PATH = "/WEB-INF/statics/img/";

    // Keys in properties exposed to the view
    private final static String CDN_BASE_KEY = "cdnBase";
    private final static String ACTIVE_SPRING_PROFILES_KEY = "activeSpringProfiles";

    @Autowired
    private Environment environment;

    @Value("/statics/${pom.version}/css/*")
    private String verionedCssPath;

    @Value("/statics/${pom.version}/js/*")
    private String versionedJsPath;

    @Value("/statics/${pom.version}/img/*")
    private String verionedImgPath;

    @Value("${mvc.cdnBase:}")
    private String cdnBase;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(verionedCssPath)
                .addResourceLocations(CSS_PATH)
                .setCachePeriod(Integer.MAX_VALUE);

        registry.addResourceHandler(versionedJsPath)
                .addResourceLocations(JS_PATH)
                .setCachePeriod(Integer.MAX_VALUE);

        registry.addResourceHandler(verionedImgPath)
                .addResourceLocations(IMG_PATH)
                .setCachePeriod(Integer.MAX_VALUE);
    }

    @Bean(name = "viewProperties")
    public PropertiesFactoryBean viewProperties() {

        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("build.properties"));

        Properties p = new Properties();

        p.setProperty(
                CDN_BASE_KEY, 
                cdnBase);

        p.setProperty(
                ACTIVE_SPRING_PROFILES_KEY, 
                Arrays.toString(
                        environment
                                .getActiveProfiles()));

        bean.setProperties(p);

        return bean;
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setCache(true);
        resolver.setExposedContextBeanNames("viewProperties");
        resolver.setExposeContextBeansAsAttributes(true);

        return resolver;
    }
}
