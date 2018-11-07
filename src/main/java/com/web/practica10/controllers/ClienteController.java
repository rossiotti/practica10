package com.web.practica10.controllers;

import com.web.practica10.entity.Client;
import com.web.practica10.service.ClientService;
import com.web.practica10.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClientService cs;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/indexUser", method = RequestMethod.GET)
    public ModelAndView indexUser() {
        ModelAndView model = new ModelAndView();
        List<Client> c = cs.listClients();
        model.addObject("clientes",c);
        model.setViewName("Cliente/indexUsuarios");
        return model;
    }
    @RequestMapping(value = "/crearUser", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("Cliente/crearClient", "cliente", new Client());
    }

    @RequestMapping(value = "/crearUser", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("file") MultipartFile file, @ModelAttribute("cliente")Client client) {
        client.setEnabled(true);
        if(file != null){
            String fileName = fileStorageService.storeFile(file);
            client.setPhoto(fileName);
        }

        cs.createClient(client);


        return indexUser();
    }

    @RequestMapping(value = "/borrarUser", method = RequestMethod.GET)
    public ModelAndView submit(@RequestParam("id") int id) {


        cs.deleteClient(cs.findClient(id));

        return indexUser();
    }

}
