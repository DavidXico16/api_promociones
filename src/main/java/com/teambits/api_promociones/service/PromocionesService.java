package com.teambits.api_promociones.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.teambits.api_promociones.model.DatosPromociones;
import com.teambits.api_promociones.model.GeneralResponse;
import com.teambits.api_promociones.model.Grafos;
import com.teambits.api_promociones.model.PromocionesTTP;
import com.teambits.api_promociones.repository.DatosPromocionesRepository;
import com.teambits.api_promociones.repository.GrafosRepository;
import com.teambits.api_promociones.repository.PromocionesRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromocionesService {

    @Autowired
    PromocionesRepository repo;

    @Autowired
    DatosPromocionesRepository repoDatos;

    @Autowired
    GrafosRepository repoGrafosRepository;

    public GeneralResponse setDatos( JsonNode request) throws Exception {

        DatosPromociones datos = new DatosPromociones();

        datos.setIdFlujo( request.get("idFlujo").asText() );
        datos.setNombrePromocion( request.get("datos").get("nombrePromocion").asText() );
        datos.setNombreHomologado( request.get("datos").get("nombreHomologado").asText() );
        datos.setDescripcion( request.get("datos").get("descripcion").asText() );
        datos.setAreaResponsable( request.get("datos").get("areaResponsable").asText() );
        //datos.setInicioVigencia( request.get("datos").get("inicioVigencia").asText() );
        //datos.setInicioVigencia( LocalDate.parse( request.get("datos").get("inicioVigencia").asText() ) );
        //datos.setFinVigencia( LocalDate.parse( request.get("datos").get("finVigencia").asText() ) );
        datos.setAreaSolicitante( request.get("datos").get("areaSolicitante").asText() );
        datos.setCategoria( request.get("datos").get("categoria").asText() );
        datos.setUnidadNegocio( request.get("datos").get("unidadNegocio").asText() );
        datos.setCancelacionEnrutamiento( request.get("datos").get("cancelacionEnrutamiento").asText() );
        datos.setCanalesFront( request.get("datos").get("canalesFront").asText() );

        log.info("Datos to save: {}", datos.toString() );

        repoDatos.save(datos);

        log.info("Request received: {}", request.toString());

        return new GeneralResponse(200, "Datos recibisdos correctamente", "OK");
    }


    public GeneralResponse getLista( ) throws Exception {

        List<PromocionesTTP> promociones = repo.findAll();
        log.info("promociones count: {}", repo.count() );

        for (PromocionesTTP promo : promociones) {
            log.info("Promotion: {}", promo.toString());
            log.info("id : {}", promo.getIdentificadorUsuario());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (PromocionesTTP promo : promociones) {
            
            ObjectNode obj = objectMapper.createObjectNode();

            obj.put("id", promo.getId() );
            obj.put("identificadorUsuario", promo.getIdentificadorUsuario());
            obj.put("tipoSolicitud", promo.getTipoSolicitud());
            obj.put("responsableCreacion", promo.getResponsableCreacion());
            obj.put("areaCreacion", promo.getAreaCreacion());


            String fechaStr = promo.getFechaCreacion(); // viene como "22-09-2025"
            if (fechaStr != null && !fechaStr.isEmpty()) {
                DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss");

                // parseamos y formateamos
                LocalDate fecha = LocalDate.parse(fechaStr, inputFmt);
                String fechaIso = fecha.atStartOfDay().format(outputFmt);

                fechaStr = fechaIso;
            }
            //2025-09-22T11:00:00
            obj.put("fechaCreacion", fechaStr);
            obj.put("estatus", promo.getEstatus());

            // añadimos el objeto al array
            arrayNode.add(obj);
        }


        String dataTest = """
            [
              {
                "id": 1,
                "identificadorUsuario": "ABC12312",
                "tipoSolicitud": "Alta Cliente",
                "responsableCreacion": "Juan Pérez",
                "areaCreacion": "Ventas",
                "fechaCreacion": "2025-09-22T10:30:00",
                "estatus": "En Apronbacion"
              },
              {
                "id": 2,
                "identificadorUsuario": "DEF45612",
                "tipoSolicitud": "Baja Cliente",
                "responsableCreacion": "María López",
                "areaCreacion": "Atención Clientes",
                "fechaCreacion": "2025-09-22T11:00:00",
                "estatus": "Activo"
              }
            ]
        """;

        //ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(dataTest);

        return new GeneralResponse(200, "Lista de grafos", arrayNode);
    }


    public GeneralResponse setDatosCondiciones( JsonNode requestCondiciones ) throws Exception {

        log.info("Request received: {}", requestCondiciones.toString());

        return new GeneralResponse(200, "Datos Condiciones recibidos correctamente", "OK");
    }

    @Transactional
    public GeneralResponse setDatosGrafo( JsonNode requestGrafo ) throws Exception {

        log.info("Request received Grafo: {}", requestGrafo.toString() );
        log.info("Request received Grafo: {}", requestGrafo.get("idflujo").asText() );

        PromocionesTTP promocionesTTP = repo.findByIdentificadorUsuario( requestGrafo.get("idflujo").asText() );

        if( promocionesTTP == null ){

            PromocionesTTP newPromo = new PromocionesTTP();

            newPromo.setIdentificadorUsuario( requestGrafo.get("idflujo").asText() );
            newPromo.setAreaCreacion("test");
            newPromo.setEstatus("Activo");
            newPromo.setResponsableCreacion("test");
            newPromo.setTipoSolicitud("test");
            newPromo.setFechaCreacion("22-09-2025");

            promocionesTTP = repo.save( newPromo);

            log.info("se creo un nuevo identificador de usuario");

            //return new GeneralResponse(400, "Identificador no encontrado", "");
        }

        Grafos grafo = new Grafos();

        System.out.println( requestGrafo.get("nodes").toString() );
        System.out.println( requestGrafo.get("idflujo").asLong());

        grafo.setGrafoData( requestGrafo.get("nodes").toString() );
        grafo.setIdPromocionesTtp( promocionesTTP.getId() );

        log.info("grafo: {}", grafo.toString() );

        repoGrafosRepository.save(grafo);

        return new GeneralResponse(200, "Datos Grafo recibidos correctamente", "OK");
    }
    
}
