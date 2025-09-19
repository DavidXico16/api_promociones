package com.teambits.api_promociones.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.teambits.api_promociones.model.GeneralResponse;
import com.teambits.api_promociones.model.PromocionesTTP;
import com.teambits.api_promociones.repository.PromocionesRepository;
import com.teambits.api_promociones.util.RSAUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromocionesService {

    @Autowired
    PromocionesRepository repo;

    public GeneralResponse setDatos( JsonNode request) throws Exception {

        String cifrado = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2NTA3NjMwOSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyfQ.u-6ShZqGLaTJdu_xTmmv3ipwj95M-kUZ0JW_BsQfeAE";
        String descifrado = RSAUtil.descifrar( cifrado );

        log.info("Decrypted data: {}", descifrado);

        PromocionesTTP existingPromo = repo.findByIdentificadorUsuario(request.get("identificadorUsuario").asText());
        if (existingPromo != null) {
            log.warn("Promotion with identificadorUsuario {} already exists", request.get("identificadorUsuario").asText());
            return new GeneralResponse(1, "Promotion already exists", existingPromo);
        }

        log.info("Request received: {}", request.toString());
        PromocionesTTP promo = new PromocionesTTP();
        promo.setIdentificadorUsuario(request.get("identificadorUsuario").asText());
        promo.setTipoSolicitud(request.get("tipoSolicitud").asText());
        promo.setFechaCreacion( new Date() );
        promo.setAreaCreacion(request.get("areaCreacion").asText());
        promo.setResponsableCreacion(request.get("responsableCreacion").asText());

        log.info("Saving promotion: {}", promo.toString());

        repo.save(promo);

        return new GeneralResponse(0, null, promo);
    }
    
}
