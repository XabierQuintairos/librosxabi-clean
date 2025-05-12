package paagbi.librosxabi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
public class Rating {

    @Id
    private String id;
    private String libro;
    private double rating;

    // Getters eta Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLibro() { return libro; }
    public void setLibro(String libro) { this.libro = libro; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
