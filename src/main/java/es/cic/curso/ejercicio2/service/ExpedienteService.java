package es.cic.curso.ejercicio2.service;

import es.cic.curso.ejercicio2.entity.Expediente;
import es.cic.curso.ejercicio2.repository.ExpedienteRepository;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    
    public long crear(Expediente expediente) {
  
        System.out.println("checkpoint 2");

        Expediente savedExpediente = expedienteRepository.save(expediente);
        return savedExpediente.getId();
    }


    
    // Listar todos los expedientes
    @Transactional(readOnly = true)
    public List<Expediente> listar() {
        /*PRIMERA FORMa
        Iterable<Expediente> expedientes = expedienteRepository.findAll();
        List<Expediente> listaExpedientes = new ArrayList<>();
        expedientes.forEach(listaExpedientes::add);
        return listaExpedientes;*/

        //SEGUNDA FORMA 

        return expedienteRepository.findAll();
      
    }

    // Leer un expediente por su ID
    @Transactional(readOnly = true)
    public Expediente leer(long id) {
           Expediente expediente = expedienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("No encontrado", id)));
    Hibernate.initialize(expediente.getDocumentos());  // Forzando la inicialización
    return expediente;
    }

    // Actualizar un expediente existente
    //En vez de todo puede ponerse solo  expedienteRepository.save(expediente);
    
    public void actualizar(Expediente expediente) {
        if (expediente.getId() == null) {
            throw new RuntimeException("ID no proporcionado para la actualización");
        }
        expediente.getDocumentos().forEach(doc -> doc.setExpediente(expediente));
        expedienteRepository.save(expediente);
    }

    // Borrar un expediente por su ID
    public void borrar(long id) {
        expedienteRepository.deleteById(id);
    }
}

