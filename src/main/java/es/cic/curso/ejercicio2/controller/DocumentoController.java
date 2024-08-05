package es.cic.curso.ejercicio2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.ejercicio2.entity.Documento;
import es.cic.curso.ejercicio2.service.DocumentoService;

@RestController
@RequestMapping("/documento")
public class DocumentoController {
 
@Autowired
DocumentoService documentoService;

    @PostMapping(value="/crear")

    /*
     *
     * 
     * curl -X POST http://localhost:8080/documento/crear -H
     * "Content-Type: application/json" -d 
     * '{
                "titulo":"titulodocumento",
                "contenido":"contenido1",
                "expediente":{"id":1}
        }'
     * 

     */
    @ResponseStatus(HttpStatus.CREATED)
    public long crear(@RequestBody Documento documento) {
        return documentoService.crear(documento);
    }

    // Leer un documento por su ID
    @GetMapping(value = "/{id}")
    public Documento leer(@PathVariable long id) {
        Documento documento = documentoService.leer(id);
        if (documento == null) {
            throw new RuntimeException("Documento no encontrado para el ID: " + id);
        }
        return documento;
    }

    // Listar todos los expedientes
    @GetMapping
    public List<Documento> listar() {
        return documentoService.listar();
    }

    // Actualizar un expediente existente
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void actualizar(@PathVariable Long id, @RequestBody Documento documento) {
        documento.setId(id);
        documentoService.actualizar(documento);
    }

    // Borrar un expediente por su ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable long id) {
        documentoService.borrar(id);
    }
}
