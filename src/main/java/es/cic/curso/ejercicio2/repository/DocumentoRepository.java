package es.cic.curso.ejercicio2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.ejercicio2.entity.Documento;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento,Long>{

}
