package es.cic.curso.ejercicio2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.cic.curso.ejercicio2.entity.Documento;
import es.cic.curso.ejercicio2.service.DocumentoService;

import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public Documento crearDocumento(@RequestBody Documento documento) {
        return documentoService.crear(documento);
    }

    @GetMapping("/{id}")
    public Documento obtenerDocumento(@PathVariable Long id) {
        return documentoService.obtener(id);
    }

    @PutMapping("/{id}")
    public Documento actualizarDocumento(@PathVariable Long id, @RequestBody Documento documento) {
        documento.setId(id);
        return documentoService.actualizar(documento);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminar(id);
    }

    @GetMapping
    public List<Documento> listarDocumentos() {
        return documentoService.listar();
    }
}
