package com.teambits.api_promociones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.teambits.api_promociones.model.GeneralResponse;
import com.teambits.api_promociones.service.PromocionesService;

@RestController
@RequestMapping("/promociones")
public class PromocionesController {

    @Autowired
    PromocionesService service;

    @PostMapping("/datos")
    public GeneralResponse datosdPromocionesTTP(@RequestBody JsonNode promo) throws Exception {
        return service.setDatos(promo);
    }
    
}
