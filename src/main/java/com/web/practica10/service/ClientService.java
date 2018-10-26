package com.web.practica10.service;

import com.web.practica10.entity.Client;
import com.web.practica10.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional
    public void createClient(Client client){

        clientRepository.save(client);

    }

    public Client findClient(int id){

        return clientRepository.findById(id);
    }

    @Transactional
    public void deleteClient(Client client){

        client.setEnabled(false);
        clientRepository.save(client);
    }

    public List<Client> listClients(){

        return clientRepository.findAllByEnabled(true);
    }
}
