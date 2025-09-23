package com.teambits.api_promociones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teambits.api_promociones.model.DatosPromociones;

@Repository
public interface DatosPromocionesRepository extends JpaRepository<DatosPromociones, Long> {


    
}
