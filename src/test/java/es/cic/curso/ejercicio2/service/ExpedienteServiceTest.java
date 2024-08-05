package es.cic.curso.ejercicio2.service;



import es.cic.curso.ejercicio2.entity.Documento;
import es.cic.curso.ejercicio2.entity.Expediente;
import es.cic.curso.ejercicio2.repository.DocumentoRepository;
import es.cic.curso.ejercicio2.repository.ExpedienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpedienteServiceTest {

    @Autowired
    ExpedienteRepository expedienteRepository;
    @Autowired
    DocumentoRepository documentoRepository;

    @PersistenceContext
    EntityManager entityManager;

    // Expediente
    private Expediente expediente;
    // Documento
    private Documento documento;
    @Autowired
    private ExpedienteService expedienteService;

    // Método que se ejecuta antes de cada prueba
    @BeforeEach
    public void setUp() {
        // Creación de un expediente de prueba
        expediente = new Expediente();
        expedienteRepository.save(expediente);

        // Creacion de un documento de prueba
        documento = new Documento();
        documento.setContenido("Contenido documento");

        // Para que funcione el insert de documento correctamente y se aplique
        documento.setExpediente(expediente);
        expediente.getDocumentos().add(documento);
        expedienteRepository.save(expediente);
        expedienteRepository.flush();

    }

    // Método que se ejecuta después de cada prueba
    @AfterEach
    @Rollback
    public void tearDown() {

    }

        @Test
    void testCrear() {
        Expediente nuevoExpediente = new Expediente();
        expedienteService.crear(nuevoExpediente);

        assertNotNull(nuevoExpediente.getId(), "El expediente no fue creado correctamente");
    }

       @Test
    void testLeer() {
        Expediente expedienteLeido = expedienteService.leer(expediente.getId());

        assertNotNull(expedienteLeido, "El expediente no fue encontrado");
        assertEquals(expediente.getId(), expedienteLeido.getId(), "El expediente leído no coincide con el creado");
    }

    @Test
    void testListar() {
        List<Expediente> res = expedienteService.listar();

        assertTrue(res.size() >= 1, "No existe al menos el registro esperado");
    }

    @Test
    public void testActualizar() {
        // Leer expediente desde la base de datos
        Expediente expedienteLeido = expedienteService.leer(expediente.getId());
        // Modificar el nombre del expediente
        expedienteLeido.setNombre("Ninguno");
    
        // Actualizar el expediente
        expedienteService.actualizar(expedienteLeido);
    
        // Leer nuevamente el expediente desde la base de datos
        Expediente expedienteActualizado = expedienteService.leer(expediente.getId());
        // Verificar que el nombre haya sido actualizado correctamente
        assertEquals("Ninguno", expedienteActualizado.getNombre(), "El nombre del expediente no fue actualizado correctamente");
    }
    

    @Test
    void testBorrar(){
        expedienteService.borrar(expediente.getId());

        Expediente expedienteBorrado = expedienteService.leer(expediente.getId());
        assertNull(expedienteBorrado, "El expediente no fue borrado correctamente");
    }

}
