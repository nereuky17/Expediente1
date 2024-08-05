
package es.cic.curso.ejercicio2.entity;





import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String contenido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "expediente_id")
   
    private Expediente expediente;

    public Documento() {
    }

    public Documento(String titulo, String contenido, Expediente expediente) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.expediente = expediente;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((contenido == null) ? 0 : contenido.hashCode());
        result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
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
        Documento other = (Documento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (contenido == null) {
            if (other.contenido != null)
                return false;
        } else if (!contenido.equals(other.contenido))
            return false;
        if (expediente == null) {
            if (other.expediente != null)
                return false;
        } else if (!expediente.equals(other.expediente))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Documento [id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + "]";
    }

  
}
