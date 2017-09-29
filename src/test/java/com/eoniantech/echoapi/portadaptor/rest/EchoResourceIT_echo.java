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
package com.eoniantech.echoapi.portadaptor.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for the EchoResource's echo endpoint.
 * 
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
public class EchoResourceIT_echo {

    private static final String URL 
            = "http://localhost:8180/echo/api/";

    private static Client httpClient;

    @BeforeClass
    public static void before() {
        httpClient = ClientBuilder.newClient(); 
    }

    @Test
    public void testEcho() {
        WebTarget webTarget 
                = httpClient
                        .target(URL)
                        .path("echo-message")
                        .queryParam("message", "hello");

        Invocation invocation 
                = webTarget
                        .request()
                        .buildGet();
        
        Response response 
                = invocation
                        .invoke();

        assertEquals(200, response.getStatus());
        assertEquals("hello", response.readEntity(String.class));
    } 
}
