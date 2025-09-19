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
@Table(name = "promociones_TTP")
public class PromocionesTTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // porque usaste IDENTITY en Oracle
    @Column(name = "id_promociones_TTP")
    private Long id;

    @Column(name = "identificador_usuario", nullable = false, length = 36)
    private String identificadorUsuario;

    @Column(name = "tipo_solicitud", nullable = false, length = 39)
    private String tipoSolicitud;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "area_creacion", nullable = false, length = 39)
    private String areaCreacion;

    @Column(name = "responsable_creacion", nullable = false, length = 39)
    private String responsableCreacion;
    
}
