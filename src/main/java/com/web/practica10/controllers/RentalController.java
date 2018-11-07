package com.web.practica10.controllers;

import com.web.practica10.entity.Rental;
import com.web.practica10.service.EquipService;
import com.web.practica10.service.FileStorageService;
import com.web.practica10.service.RentService;
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
public class RentalController {

        @Autowired
        private RentService rentService;

        @Autowired
        private EquipService equipService;

        @Autowired
        private FileStorageService fileStorageService;

        @RequestMapping(value = "/indexAlquiler", method = RequestMethod.GET)
        public ModelAndView indexEquipos() {
            ModelAndView model = new ModelAndView();
            List<Rental> e = rentService.rentalList();
            model.addObject("alquileres",e);
            model.setViewName("indexAlquiler");
            return model;
        }

        @RequestMapping(value = "/crearAlquiler", method = RequestMethod.GET)
        public ModelAndView showForm() {
            return new ModelAndView("crearAlquiler", "alquiler", new Rental());
        }

      /*  @RequestMapping(value = "/crearAlquiler", method = RequestMethod.POST)
        public ModelAndView submit(@RequestParam("file") MultipartFile file, @ModelAttribute("alquiler")Rental rental) {

            if(file != null){
                String fileName = fileStorageService.storeFile(file);
                equip.setPhoto(fileName);
            }

            equipService.createEquip(equip);


            return indexEquipos();
        }
    }*/

}
