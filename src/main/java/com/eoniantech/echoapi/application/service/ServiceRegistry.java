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
package com.eoniantech.echoapi.application.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring-backed Application Registry containing beans that are in the 
 * Application layer.
 *
 * @author Michael Andrews | Michael.Andrews@eoniantech.com
 * @since 1.0
 */
public class ServiceRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * @return {@link EchoService}
     */
    public static EchoService echoService() {
        return getBean(EchoService.class);
    }    

    /**
     * Sets the Spring context. This method is automatically called by Spring
     * once the context is created.
     *
     * @param applicationContext
     */
    @Override
    public synchronized void setApplicationContext(
            ApplicationContext applicationContext) {

        ServiceRegistry.applicationContext = applicationContext;
    }

    /**
     * Generic "bean getter". This method looks in the Spring context for a
     * bean of type T.
     *
     * @param <T>
     * @param clazz the class of the bean
     * @return the bean
     */
    private static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    
}
