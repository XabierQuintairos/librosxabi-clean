package paagbi.librosxabi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "editoriales")
public class Editorial {

    @Id
    private String id;
    private String nombre;
    private int fundacion;

    // Getters eta Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getFundacion() { return fundacion; }
    public void setFundacion(int fundacion) { this.fundacion = fundacion; }
}
