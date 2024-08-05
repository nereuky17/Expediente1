
package es.cic.curso.ejercicio2.repository;

import es.cic.curso.ejercicio2.entity.Expediente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
// extends CrudRepository<Expediente, Long>
public interface ExpedienteRepository extends JpaRepository<Expediente,Long>{
    
    @Query(value = "select e from Expediente e where e.nombre = :nombre" )
    public List<Expediente> findByNombre(String nombre);
    
}
