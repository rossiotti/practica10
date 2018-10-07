package com.web.practica10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DashboardController {

        @RequestMapping(value = "/admin", method = RequestMethod.GET)
        public ModelAndView admin() {
            ModelAndView model = new ModelAndView();
            model.setViewName("admin");
            return model;
        }

        @RequestMapping(value = "/user", method = RequestMethod.GET)
        public ModelAndView user() {
            ModelAndView model = new ModelAndView();
            model.setViewName("user");
            return model;
        }


    }
