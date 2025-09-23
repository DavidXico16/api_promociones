package com.teambits.api_promociones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teambits.api_promociones.model.PromocionesTTP;

@Repository
public interface PromocionesRepository extends JpaRepository<PromocionesTTP, Long> {

    PromocionesTTP findByIdentificadorUsuario(String identificadorUsuario);

    List<PromocionesTTP> findAll();
    
}
