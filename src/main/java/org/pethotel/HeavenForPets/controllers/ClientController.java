package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Paulina on 2017-10-19.
 */
@RestController
@RequestMapping("/clients")
public class ClientController implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    OwnerService ownerService;

    @GetMapping("/getAllClients")
    public List<String> getAllClients(){
        return ownerService.getAllClients();
    }

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
