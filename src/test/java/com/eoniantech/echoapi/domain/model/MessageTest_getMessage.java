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
package com.eoniantech.echoapi.domain.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the {@link Message} model's message method.
 * 
 * @author Michael Andrews | Michael.Andrews@eoniantech.com
 * @since 1.0
 */
public class MessageTest_getMessage {

    private Message message;

    @Before
    public void before() {
        message = new Message("This is a message");
    }

    @Test
    public void testGetMessage() {
        assertEquals("This is a message", message.message());
    } 
}
