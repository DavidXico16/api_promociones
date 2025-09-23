package com.teambits.api_promociones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "GRAFO")
public class Grafos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRAFO")
    private Long idGrafo;

    @Column(name = "ID_PROMOCIONES_TTP", nullable = false)
    private Long idPromocionesTtp;

    @Lob
    @Column(name = "GRAFO_DATA")
    private String grafoData;
    
}
