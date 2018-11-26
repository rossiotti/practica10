package com.web.practica10.controllers;

import com.web.practica10.entity.EquipRental;
import com.web.practica10.entity.Familia;
import com.web.practica10.entity.Rental;
import com.web.practica10.service.ClientService;
import com.web.practica10.service.FamiliaService;
import com.web.practica10.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class DashboardController {

    @Autowired
    private FamiliaService familiaService;


    @Autowired
    private ClientService cs;

    @Autowired
    private RentService rentService;


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
        public ModelAndView admin(Model mod, HttpSession sesion, HttpServletRequest request) {
            ModelAndView model = new ModelAndView();
        List<Rental> rentalList = rentService.rentalListAll();
        List<FamDias> famDias = new ArrayList<>();
        int diasTotal = 0 ;
        for (Rental r:rentalList) {
            for (EquipRental e:r.getEquipRental()) {
                if(e.getReturned()){
                    FamDias f = new FamDias();
                    f.setLabel(e.getEquip().getFamily().getNombre());
                    diasTotal+= e.getDias();
                    f.setY(e.getDias());
                    famDias.add(f);
                    FamDias f2 = new FamDias();
                    if(e.getEquip().getSubFamily() != null) {
                        f2.setY(e.getDias());
                        f2.setLabel("("+f.getLabel()+") "+e.getEquip().getSubFamily().getNombre());
                        famDias.add(f2);
                    }
                }
            }
        }

        for (int i = 0; i < famDias.size() ; i++) {
            for (int j = i+1; j <famDias.size() ; j++) {
                if(famDias.get(i).getLabel().equals(famDias.get(j).getLabel())){
                    famDias.get(i).setY(famDias.get(i).getY() + famDias.get(j).getY());
                    famDias.remove(famDias.get(j));
                   j--;
                }
            }
        }


        model.setViewName("admin");
        model.addObject("famDias",famDias);
        model.addObject("diasTotales",diasTotal);
        model.addObject("puerto", ""+request.getLocalPort());
        return model;
        }
    @RequestMapping(value = "/historial", method = RequestMethod.GET)
    public ModelAndView historial(Model mod, HttpSession sesion, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("historial");
        model.addObject("clientes",cs.listClients());
        return model;
    }

    @RequestMapping(value = "/historial", method = RequestMethod.POST)
    public ModelAndView historial(@RequestParam("selectClient") int id) {

        ModelAndView model = new ModelAndView();

        List<Rental> rentalList = rentService.rentalsByClient(cs.findClient(id));

        model.setViewName("historial");
        model.addObject("clientes",cs.listClients());
        model.addObject("Cliente",cs.findClient(id).getName());
        model.addObject("rentals",rentalList);
        return model;

    }


        @RequestMapping(value = "/user", method = RequestMethod.GET)
        public ModelAndView user() {
            ModelAndView model = new ModelAndView();
            model.setViewName("user");
            return model;
        }

        class FamDias{

        String label;
        int y;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }


    }
