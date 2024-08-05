
package es.cic.curso.ejercicio2.entity;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    //

    @OneToMany(mappedBy = "expediente", cascade={CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
  
    private List<Documento> documentos=new ArrayList<>();

    
    public Expediente() {
    }

    public Expediente(String nombre) {
        this.nombre = nombre;
    }

    
    public Expediente(String nombre, List<Documento> documentos) {
        this.nombre = nombre;
        this.documentos = documentos;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public void addDocumento(Documento documento) {
        documentos.add(documento);
        documento.setExpediente(this);
    }

    public void removeDocumento(Documento documento) {
        documentos.remove(documento);
        documento.setExpediente(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((documentos == null) ? 0 : documentos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Expediente other = (Expediente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (documentos == null) {
            if (other.documentos != null)
                return false;
        } else if (!documentos.equals(other.documentos))
            return false;
        return true;
    }

}
