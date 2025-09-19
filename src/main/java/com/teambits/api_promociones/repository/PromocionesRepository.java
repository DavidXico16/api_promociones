package com.teambits.api_promociones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teambits.api_promociones.model.PromocionesTTP;

public interface PromocionesRepository extends JpaRepository<PromocionesTTP, Long> {

    PromocionesTTP findByIdentificadorUsuario(String identificadorUsuario);
    
}
