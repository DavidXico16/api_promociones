package com.teambits.api_promociones.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "PROMOCIONES_TTP") // en mayúsculas
public class PromocionesTTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // porque usaste IDENTITY
    @Column(name = "ID_PROMOCIONES_TTP")
    private Long id;

    @Column(name = "IDENTIFICADOR_USUARIO", nullable = false, length = 36)
    private String identificadorUsuario;

    @Column(name = "TIPO_SOLICITUD", nullable = false, length = 39)
    private String tipoSolicitud;

    @Column(name = "RESPONSABLE_CREACION", nullable = false, length = 39)
    private String responsableCreacion;
    
    @Column(name = "AREA_CREACION", nullable = false, length = 39)
    private String areaCreacion;

    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;

    @Column(name = "ESTATUS", nullable = false)
    private String estatus; // NUMBER(38) mejor mapear a Long
}