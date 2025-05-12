package paagbi.librosxabi.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class Author {

    private String nombre;

    // Herrialdeen zerrenda erabiliko dugu, egile batek hainbat herrialde izan ditzakeelako.
    
    private String herrialdea;


    public Author() {
    }

    public Author(String nombre, String herrialdea) {
        this.nombre = nombre;
        this.herrialdea = herrialdea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHerrialdea() {
        return herrialdea;
    }

    public void setHerrialdea(String herrialdea) {
        this.herrialdea = herrialdea;
    }
}
