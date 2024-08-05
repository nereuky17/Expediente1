package es.cic.curso.ejercicio2.service;

import es.cic.curso.ejercicio2.entity.Documento;
import es.cic.curso.ejercicio2.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public long crear(Documento documento) {
         Documento savedDocumento = documentoRepository.save(documento);
         System.out.println(savedDocumento);
        return savedDocumento.getId();
    }

    public Documento leer(Long id) {
        return documentoRepository.findById(id).orElse(null);
    }

    public Documento actualizar(Documento documento) {
        if (documento.getId() == null) {
            throw new RuntimeException("ID no proporcionado para la actualización");
        }
        return documentoRepository.save(documento);
    }

    public void borrar(Long id) {
        documentoRepository.deleteById(id);
    }

    public List<Documento> listar() {
        return (List<Documento>) documentoRepository.findAll();
    }
}

