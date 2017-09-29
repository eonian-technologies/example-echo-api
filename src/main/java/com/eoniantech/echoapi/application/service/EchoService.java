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

import com.eoniantech.echoapi.domain.model.Message;

/**
 * Application service.
 * 
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
public class EchoService {

    /**
     * Returns the same message that is passe in. A {@link Message} model is
     * created which will validate user input. The model is the used to return
     * the same message that was passed in.
     * 
     * @param message the message.
     * @return the message.
     * @see Message
     */
    public String echoMessage(String message) {
        return new Message(message).message(); 
    } 
}
