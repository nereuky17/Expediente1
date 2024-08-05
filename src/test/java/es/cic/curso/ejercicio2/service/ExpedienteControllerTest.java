package es.cic.curso.ejercicio2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.curso.ejercicio2.entity.Expediente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ExpedienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    // Expediente
    private Expediente expediente;

    // Método que se ejecuta antes de cada prueba
    @BeforeEach
    public void setUp() {
        
        // Creación de un expediente de prueba
        expediente = new Expediente();
        expediente.setNombre("Expediente Test");
        // Persistencia del expediente en la base de datos para usar en las pruebas
        entityManager.persist(expediente);
        entityManager.flush();

        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();
    }

    // Método que se ejecuta después de cada prueba
    @AfterEach
    @Rollback
    public void tearDown() {

    }

    @Test
    public void testCrearExpediente() throws Exception {
        String expedienteJson = objectMapper.writeValueAsString(new Expediente("Nuevo Expediente"));
        mvc.perform(post("/expediente/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expedienteJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testListar() throws Exception {
        mvc.perform(get("/expediente/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testLeer() throws Exception {
        //assertEquals(expediente.getNombre(), expedienteResultado.getNombre());*/
        MvcResult mvcResult = mvc.perform(get("/expediente/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expediente.getId()))
                .andReturn();

                    
                
            String body = mvcResult.getResponse().getContentAsString();
            Expediente expedienteResultado = objectMapper.readValue(body, Expediente.class);
        assertTrue(expedienteResultado.getDocumentos().size() >=0);
      
     
       
    }

    @Test
    public void testActualizar() throws Exception {
        expediente.setNombre("Expediente Actualizado");
        String mensaje = objectMapper.writeValueAsString(expediente);

        mvc.perform(put("/expediente/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mensaje))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testBorrar() throws Exception {
        mvc.perform(delete("/expediente/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }
}
