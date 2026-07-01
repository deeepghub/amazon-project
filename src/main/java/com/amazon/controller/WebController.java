package com.amazon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Forwards all React Router frontend routes to index.html.
 * Without this, refreshing /product/5 or /cart gives a 404 from Spring Boot
 * because it looks for a amazon endpoint instead of serving the React app.
 */
@Controller
public class WebController {

    @RequestMapping(value = {
            "/",
            "/login",
            "/register",
            "/cart",
            "/add_product",
            "/product/**",
            "/product/update/**"
    })
    public String forward() {
        return "forward:/index.html";
    }
}