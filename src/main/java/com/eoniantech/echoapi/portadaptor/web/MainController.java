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
package com.eoniantech.echoapi.portadaptor.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC controller for the index page.
 * 
 * @author Michael Andrews <Michael.Andrews@eoniantech.com>
 * @since 1.0
 */
@Controller
public class MainController {

    private static final String INDEX_PAGE = "index";

    @RequestMapping("/")
    public String indexPage() { 
        return INDEX_PAGE;
    }
}