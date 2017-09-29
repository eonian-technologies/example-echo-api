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

import java.util.Objects;

/**
 * Class representing a message.
 * 
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
public class Message {

    private static final String MESSAGE_CAN_NOT_BE_NULL
            = "The message can not be null.";

    private static final String MESSAGE_CAN_NOT_BE_EMPTY
            = "The message can not be empty.";

    private String message;

    /**
     * Constructor.
     * 
     * @param message 
     */
    public Message(String message) {
        super();
        setMessage(message); 
    }

    /**
     * @return the message.
     */
    public String message() {
        return message;
    } 

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.message);
        return hash;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        return Objects.equals(this.message, other.message);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Message [");
        builder.append("message=").append(message);
        builder.append("]");

        return builder.toString();
    } 

    /**
     * Sets the message member ensuring that the input is not null and not 
     * empty.
     * 
     * @param message the message to set. 
     */
    private void setMessage(String message) {
        if (message == null)
            throw new IllegalArgumentException(
                    MESSAGE_CAN_NOT_BE_NULL);

        if (message.trim().isEmpty()) 
            throw new IllegalArgumentException(
                    MESSAGE_CAN_NOT_BE_EMPTY);

        this.message = message;
    }
}
