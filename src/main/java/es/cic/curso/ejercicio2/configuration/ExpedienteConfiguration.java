package es.cic.curso.ejercicio2.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/los.properties")
public class ExpedienteConfiguration{
@Value("${ejercicio2.expediente.valor}")
private long valor;

public long getValor() {
    return valor;
}

public void setValor(long valor) {
    this.valor = valor;
}


}