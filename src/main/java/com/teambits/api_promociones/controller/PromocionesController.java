package com.teambits.api_promociones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.teambits.api_promociones.model.GeneralResponse;
import com.teambits.api_promociones.service.PromocionesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PromocionesController {

    @Autowired
    PromocionesService service;

    @PostMapping("/datosPromocion")
    public GeneralResponse datosdPromocionesTTP(@RequestBody JsonNode promo) throws Exception {
        return service.setDatos(promo);
    }

    @PostMapping("/condiciones")
    public GeneralResponse datosCOndiciones( @RequestBody JsonNode condiciones ) throws Exception {
        log.info("__datosCOndiciones__");
        return service.setDatosCondiciones( condiciones );
    }

    @PostMapping("/grafo")
    public GeneralResponse datopsGrafo( @RequestBody JsonNode condiciones ) throws Exception {
        log.info("__datopsGrafo__");
        return service.setDatosGrafo( condiciones );
    }

    @PostMapping("/listaGrafos")
    public GeneralResponse listaGrafos( ) throws Exception {
        log.info("__listaGrafos__");
        return service.getLista();
    }
    
}
