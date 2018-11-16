package com.web.practica10.controllers;

import com.web.practica10.entity.Familia;
import com.web.practica10.service.FamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    @RequestMapping(value = "/indexFamilia", method = RequestMethod.GET)
    public ModelAndView indexFamilia() {
        ModelAndView model = new ModelAndView();
        List<Familia> e = familiaService.getFamilias(true);
        model.addObject("familias",e);
        model.setViewName("indexFamilia");
        return model;
    }

    @RequestMapping(value = "/crearFamilia", method = RequestMethod.GET)
    public ModelAndView crearFam() {
        ModelAndView model = new ModelAndView();
        model.addObject("familias",familiaService.getFamilias(true));
        model.addObject("familia",new Familia());
        model.setViewName("crearFamilia");
        return model;
    }

    @RequestMapping(value = "/crearFamilia", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("familia")Familia fam) {
        fam.setEnabled(true);
        familiaService.saveFam(fam);

        return indexFamilia();
    }

}
