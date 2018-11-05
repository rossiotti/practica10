package com.web.practica10.controllers;

import com.web.practica10.entity.Client;
import com.web.practica10.repositories.ClientRepository;
import com.web.practica10.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClientService cs;

    @RequestMapping(value = "/indexUser", method = RequestMethod.GET)
    public ModelAndView indexUser() {
        ModelAndView model = new ModelAndView();
        List<Client> c = cs.listClients();
        model.addObject("clientes",c);
        model.setViewName("indexUsuarios");
        return model;
    }
    @RequestMapping(value = "/crearUser", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("crearClient", "cliente", new Client());
    }

    @RequestMapping(value = "/crearUser", method = RequestMethod.POST)
    public String submit(@ModelAttribute("cliente")Client client) {

        cs.createClient(client);
        return "indexUsuarios";
    }

}
