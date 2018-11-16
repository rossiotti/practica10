package com.web.practica10.controllers;

import com.web.practica10.entity.Equip;
import com.web.practica10.entity.Rental;
import com.web.practica10.service.ClientService;
import com.web.practica10.service.EquipService;
import com.web.practica10.service.FileStorageService;
import com.web.practica10.service.RentService;
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
    private ClientService cs;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/indexAlquiler", method = RequestMethod.GET)
    public ModelAndView indexEquipos() throws ParseException {
        ModelAndView model = new ModelAndView();
        List<Rental> e = rentService.rentalList();
        model.addObject("alquileres",e);
        model.setViewName("indexAlquiler");
        return model;
    }

    @RequestMapping(value = "/indexEntrega", method = RequestMethod.GET)
    public ModelAndView indexEntrega() throws ParseException {
        ModelAndView model = new ModelAndView();
        float c = 0;
        List<Rental> e = rentService.rentalListTodo();
        for (Rental r:e
                ) {
            for (Equip equip:r.getEquipRental()
                    ) {
                c+= equip.getTariff() * r.getDiasRent();
            }
            rentService.setCosto(r,c);

        }

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
        for (Equip e: rental.getEquipRental()
             ) {
            Date firstDate = sdf.parse(hoy);
            Date secondDate = sdf.parse(rental.getDate());
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            System.out.println(diff);
            if(diff <= 0){
                e.setDiasRentados(1);
                e.setCostoRenta(e.getTariff() * 1);
            }else{
                e.setDiasRentados((int) diff);
                e.setCostoRenta(e.getTariff() * (int) diff);
            }

        }
        model.addObject("alquiler",rental);
        model.setViewName("crearEntrega");
        return model;
    }

    @RequestMapping(value = "/crearEntrega", method = RequestMethod.POST)
    public ModelAndView crearEntrega(@RequestParam("chk") List<Integer> id_equipos,@ModelAttribute("alquiler")Rental rental) throws ParseException {

        for (Integer inte: id_equipos
             ) {
            for (Equip e:rental.getEquipRental()
                 ) {
                if(inte == e.getId()){
                    e.setStock(e.getStock()+e.getStockRent());
                 //   e.setStockRent(e.getStockRent()-);
                }
            }
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

        ArrayList<Integer> stock = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        rental.setDate(dateFormat.format(date));

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
        Set<Equip> rentados = new HashSet<>();
        for (int i = 0; i < listEquip.size(); i++) {
            for (int j = 0; j < checks.size() ; j++) {
                if(listEquip.get(i).getId() == checks.get(j)){
                    for (int k = 0; k < stocks.size() ; k++) {
                        if(i == k){
                            Equip e = new Equip();
                            e.setFamily(listEquip.get(i).getFamily());
                            e.setName(listEquip.get(i).getName());
                            e.setPhoto(listEquip.get(i).getPhoto());
                            e.setSubFamily(listEquip.get(i).getSubFamily());
                            e.setTariff(listEquip.get(i).getTariff());
                            e.setStockRent(listEquip.get(i).getStockRent()+ stocks.get(k));
                            e.setStock(listEquip.get(i).getStock()-stocks.get(k));
                            equipService.editarStock(listEquip.get(i),e.getStock(),e.getStockRent());
                            rentados.add(e);
                        }
                    }
                }
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(rental.getDeliveryDate());
        Date secondDate = sdf.parse(rental.getDate());
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        rental.setDiasRent((int) diff);
        rental.setEquipRental(rentados);
        rental.setEquipStock(total);
        rental.setPending(true);
        rentService.createRent(rental);
        return indexEquipos();
    }
}


