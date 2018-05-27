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

import com.eoniantech.echoapi.portadaptor.rest.EchoResource;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import static org.glassfish.jersey.server.ServerProperties.APPLICATION_NAME;
import static org.glassfish.jersey.server.ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED;

/**
 * REST Configuration.
 * 
 * @author Michael Andrews | Michael.Andrews@eoniantech.com
 * @since 1.0
 */
@ApplicationPath("api")
public class RestConfiguration extends ResourceConfig {

    private static final String NAME = "echo-api";

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public RestConfiguration() {

        property(
                APPLICATION_NAME,
                NAME);

        property(
                MONITORING_STATISTICS_MBEANS_ENABLED,
                true);

        register(EchoResource.class); 
    }
    
}
