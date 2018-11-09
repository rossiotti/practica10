package com.web.practica10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

        @RequestMapping(value = "/admin", method = RequestMethod.GET)
        public ModelAndView admin(Model mod, HttpSession sesion, HttpServletRequest request) {
            ModelAndView model = new ModelAndView();
            model.setViewName("admin");
            mod.addAttribute("puerto", ""+request.getLocalPort());
            return model;
        }

        @RequestMapping(value = "/user", method = RequestMethod.GET)
        public ModelAndView user() {
            ModelAndView model = new ModelAndView();
            model.setViewName("user");
            return model;
        }


    }
