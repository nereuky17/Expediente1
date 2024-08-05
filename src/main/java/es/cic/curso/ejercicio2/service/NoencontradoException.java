package es.cic.curso.ejercicio2.service;

public class NoencontradoException  extends RuntimeException{
    @SuppressWarnings("unused")
    private long id;
    public NoencontradoException() {
    }

    public NoencontradoException(String message,long id) {
        super(message);
        this.id=id;
    }

    public NoencontradoException(String message,Throwable th ,long id) {
        super(message,th);
        
    }



    
}
