package com.web.practica10.controllers;

import com.web.practica10.entity.Equip;
import com.web.practica10.service.EquipService;
import com.web.practica10.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EquipoController {

    @Autowired
    private EquipService equipService;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/indexEquipos", method = RequestMethod.GET)
    public ModelAndView indexEquipos() {
        ModelAndView model = new ModelAndView();
        List<Equip> e = equipService.listEquip(true,0);
        model.addObject("equipos",e);
        model.setViewName("indexEquipos");
        return model;
    }

    @RequestMapping(value = "/crearEquipo", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("crearEquipo", "equipo", new Equip());
    }

    @RequestMapping(value = "/crearEquipo", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("file") MultipartFile file, @ModelAttribute("equipo")Equip equip) {
        equip.setEnabled(true);

        if(file != null){
            String fileName = fileStorageService.storeFile(file);
            equip.setPhoto(fileName);
        }

        equipService.createEquip(equip);


        return indexEquipos();
    }
}
