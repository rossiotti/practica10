package com.web.practica10.controllers;

import com.web.practica10.entity.Equip;
import com.web.practica10.entity.EquipDto;
import com.web.practica10.entity.Rental;
import com.web.practica10.service.ClientService;
import com.web.practica10.service.EquipService;
import com.web.practica10.service.FileStorageService;
import com.web.practica10.service.RentService;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class RentalController {

        @Autowired
        private RentService rentService;

        @Autowired
        private EquipService equipService;

        @Autowired
        private ClientService cs;

        @Autowired
        private FileStorageService fileStorageService;

        @RequestMapping(value = "/indexAlquiler", method = RequestMethod.GET)
        public ModelAndView indexEquipos() throws ParseException {
            ModelAndView model = new ModelAndView();
            List<Rental> e = rentService.rentalList();
            for (Rental r:e
                 ) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date firstDate = sdf.parse(r.getDeliveryDate());
                Date secondDate = sdf.parse(r.getDate());
                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                r.setDiasRent(((int) diff));
            }
            model.addObject("alquileres",e);
            model.setViewName("indexAlquiler");
            return model;
        }

        @RequestMapping(value = "/crearAlquiler", method = RequestMethod.GET)
        public ModelAndView showForm() {
            ModelAndView model = new ModelAndView();

            model.addObject("alquiler",new Rental());
            model.addObject("clientes",cs.listClients());
            model.addObject("equipos",equipService.listEquip(true,0));
            model.setViewName("crearAlquiler");
            return model;
        }

       @RequestMapping(value = "/crearAlquiler", method = RequestMethod.POST)
        public ModelAndView submit(@RequestParam("index") List<Integer> index,@ModelAttribute("alquiler")Rental rental,@RequestParam("checkEquip") List<Integer> checks,@RequestParam("stockEquip") List<Integer> stocks) throws ParseException {

            ArrayList<Integer> stock = new ArrayList<>();


           int total = 0;

           for (int i = 0; i < stocks.size(); i++) {

               if(stocks.get(i) != null) {
                   int inte = stocks.get(i);
                   stock.add(i);
                   total+=inte;
                   System.out.println("Valor " + inte);
               }

           }

           List<Equip> listEquip = equipService.listEquip(true, 0);
           java.util.Set<Equip> rentados = new HashSet<>();
           for (int i = 0; i < listEquip.size(); i++) {
               for (int j = 0; j < checks.size() ; j++) {
                   if(listEquip.get(i).getId() == checks.get(j)){
                       for (int k = 0; k < index.size() ; k++) {
                           if(index.get(k) == i){
                               Equip e = listEquip.get(i);
                               e.setStockRent(e.getStockRent()+ stock.get(k));
                               e.setStock(listEquip.get(i).getStock()-stocks.get(k));
                               equipService.editarStock(listEquip.get(i),listEquip.get(i).getStock()-stocks.get(k),stocks.get(k));
                               rentados.add(e);
                           }
                       }
                   }
               }
           }
           rental.setEquipRental(rentados);
           rental.setEquipStock(total);
           rental.setPending(true);
           rentService.createRent(rental);
           return indexEquipos();
        }
    }


