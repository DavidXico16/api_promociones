package com.teambits.api_promociones.model;

import java.time.LocalDate;

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
@Table(name = "DATOS_PROMOCIONES")
public class DatosPromociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DATOS_PROMOCIONES")
    private Long idDatosPromociones;

    @Column(name = "ID_PROMOCIONES_TTP", nullable = false)
    private Long idPromocionesTtp;

    @Column(name = "ID_FLUJO", length = 20, nullable = false)
    private String idFlujo;

    @Column(name = "NOMBRE_PROMOCION", length = 100, nullable = false)
    private String nombrePromocion;

    @Column(name = "NOMBRE_HOMOLOGADO", length = 100, nullable = false)
    private String nombreHomologado;

    @Column(name = "AREA_RESPONSABLE", length = 50, nullable = false)
    private String areaResponsable;

    @Lob
    @Column(name = "TIPO_PROMOCION")
    private String tipoPromocion;

    @Column(name = "INICIO_VIGENCIA", nullable = false)
    private String inicioVigencia;

    @Column(name = "FIN_VIGENCIA", nullable = false)
    private String finVigencia;

    @Lob
    @Column(name = "AREA_SOLICITANTE")
    private String areaSolicitante;

    @Lob
    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "UNIDAD_NEGOCIO", length = 50, nullable = false)
    private String unidadNegocio;

    @Column(name = "TIPO_VENTA", length = 50, nullable = false)
    private String tipoVenta;

    @Lob
    @Column(name = "REFERENCIA")
    private String referencia;

    @Column(name = "CANCELACION_ENRUTAMIENTO", length = 200)
    private String cancelacionEnrutamiento;

    @Lob
    @Column(name = "CANALES_FRONT")
    private String canalesFront;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Column(name = "FECHA_CREACION", length = 200)
    private String fechaCreacion;
    

    @Column(name = "ULTIMA_MODIFICACION")
    private String ultimaModificacion;

    @Column(name = "RESPONSABLE_MODIFICACION", length = 100)
    private String responsableModificacion;

    
}
