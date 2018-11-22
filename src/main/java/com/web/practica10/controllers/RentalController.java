package com.web.practica10.controllers;

import com.web.practica10.entity.Equip;
import com.web.practica10.entity.EquipRental;
import com.web.practica10.entity.Rental;
import com.web.practica10.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
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
    private EquipRentService equipRentService;

    @Autowired
    private ClientService cs;


    @RequestMapping(value = "/indexAlquiler", method = RequestMethod.GET)
    public ModelAndView indexEquipos() {
        ModelAndView model = new ModelAndView();
        List<Rental> e = rentService.rentalList(true);
        model.addObject("alquileres",e);
        model.setViewName("indexAlquiler");
        return model;
    }

    @RequestMapping(value = "/indexEntrega", method = RequestMethod.GET)
    public ModelAndView indexEntrega(){
        ModelAndView model = new ModelAndView();
        float c = 0;
        List<Rental> e = rentService.rentalList(false);
        model.addObject("alquileres",e);
        model.setViewName("indexEntrega");
        return model;
    }


    @RequestMapping(value = "/crearEntrega", method = RequestMethod.GET)
    public ModelAndView crearEntrega(@RequestParam("id") int id) throws ParseException {
        ModelAndView model = new ModelAndView();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String hoy = sdf.format(new Date());
        Rental rental = rentService.findRental(id);
        for (EquipRental e: rental.getEquipRental()
             ) {
            Date firstDate = sdf.parse(hoy);
            Date secondDate = sdf.parse(rental.getDate());

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff != 0){
                equipRentService.updateEquipRent(e,(int) diff * e.getEquip().getTariff() * e.getCantidadRentada(),(int) diff);
            }else{
                equipRentService.updateEquipRent(e,1 * e.getEquip().getTariff() * e.getCantidadRentada(),1);
            }


        }
        model.addObject("alquiler",rental);
        model.setViewName("crearEntrega");
        return model;
    }

    @RequestMapping(value = "/crearEntrega", method = RequestMethod.POST)
    public ModelAndView crearEntrega(@RequestParam("chk") List<Integer> id_equipos,@RequestParam("idAlc") Integer r) throws ParseException {


        int check = 0;

        Rental rental = rentService.findRental(r);
        float total = 0;
        for (Integer i:id_equipos
             ) {
            for (EquipRental e : rental.getEquipRental()
                 ) {
                if(e.getId() == i){
                    equipRentService.updateStatus(e,true);
                    total+= e.getCostoRenta();

                }
            }
        }
        for (EquipRental ep:rental.getEquipRental()
             ) {
            if(ep.getReturned())
                check++;
        }
        if(check == rental.getEquipRental().size()){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            rentService.setCosto(rental,total);
            rentService.updateDate(rental,dateFormat.format(date));
            rentService.updateStatus(rental,false);
        }


        return indexEquipos();
    }


    @RequestMapping(value = "/crearAlquiler", method = RequestMethod.GET)
    public ModelAndView showForm() {
        ModelAndView model = new ModelAndView();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Rental rental = new Rental();
        rental.setDate(dateFormat.format(date));
        model.addObject("alquiler",rental);
        model.addObject("clientes",cs.listClients());
        model.addObject("equipos",equipService.listEquip(true,0));
        model.setViewName("crearAlquiler");
        return model;
    }

    @RequestMapping(value = "/crearAlquiler", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("index") List<Integer> index,@ModelAttribute("alquiler")Rental rental,@RequestParam("checkEquip") List<Integer> checks,@RequestParam("stockEquip") List<Integer> stocks) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = new Date();

        Set<EquipRental> rentados = new HashSet<>();
        int ind = 0;
        for (Integer i:checks
             ) {
            for (Equip e: equipService.listEquip(true,0)
                 ) {
                if(e.getId() == i){
                    EquipRental equipRental = new EquipRental();
                    equipRental.setEquip(e);
                    equipRental.setReturned(false);
                    equipRental.setCantidadRentada(stocks.get(ind));
                    equipService.editarStock(e,e.getStock()-stocks.get(ind),stocks.get(ind));
                    ind++;
                    equipRentService.createEquipRent(equipRental);
                    rentados.add(equipRental);
                }
            }
        }
        rental.setDate(sdf.format(date));
        Date firstDate = sdf.parse(rental.getDeliveryDate());
        Date secondDate = sdf.parse(rental.getDate());
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        rental.setDiasRent((int) diff);
        rental.setEquipRental(rentados);
        rental.setPending(true);
        rentService.createRent(rental);
        return indexEquipos();
    }
}


