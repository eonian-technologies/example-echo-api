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

import org.junit.Test;

/**
 * Test class for the {@link Message} model's constructor.
 * 
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
public class MessageTest_constructor {

    @SuppressWarnings("unused")
    private Message message;

    @Test
    public void testMessage() {
        message = new Message("this is a message"); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMessage_null_message() {
        message = new Message(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMessage_empty_message() {
        message = new Message("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMessage_empty_message_with_spaces() {
        message = new Message("  ");
    } 
}
