package com.tytanisukcesu.copiers.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class NotFoundServlet {

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return ("pages/404");
    }

}
