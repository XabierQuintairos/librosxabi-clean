package paagbi.librosxabi.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import paagbi.librosxabi.model.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.*;

@Repository
public class MongoDBLibroRepository implements LibroRepository {

    @Autowired
    private MongoClient client;

    private MongoCollection<Libro> libroCollection;

    @PostConstruct
    void init() {
        libroCollection = client.getDatabase("proiecto").getCollection("libros", Libro.class);
    }

    @Override
    public List<Libro> findAll() {
        return libroCollection.find().into(new ArrayList<>());
    }

    @Override
    public Optional<Libro> findById(String id) {
        return Optional.ofNullable(libroCollection.find(eq("_id", new ObjectId(id))).first());
    }

    @Override
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(new ObjectId());
            libroCollection.insertOne(libro);
        } else {
            libroCollection.replaceOne(eq("_id", libro.getId()), libro);
        }
        return libro;
    }

    @Override
    public long deleteById(String id) {
        return libroCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    @Override
    public List<Libro> findByGeneresContaining(String genero) {
        return libroCollection.find(eq("generes", genero)).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByRatingGreaterThanEqual(double rating) {
        return libroCollection.find(gte("rating", rating)).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByPriceBetween(double minPrice, double maxPrice) {
        return libroCollection.find(and(gte("price", minPrice), lte("price", maxPrice))).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByLanguage(String language) {
        return libroCollection.find(eq("language", language)).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByPublisher(String publisher) {
        return libroCollection.find(eq("publisher", publisher)).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByPageCountBetween(int minPages, int maxPages) {
        return libroCollection.find(and(gte("pageCount", minPages), lte("pageCount", maxPages)))
                .into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByVotersGreaterThanEqual(int minVoters) {
        return libroCollection.find(gte("voters", minVoters)).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByAuthorNombre(String nombre) {
        return libroCollection.find(eq("author.nombre", nombre)).into(new ArrayList<>());
    }

    @Override
    public List<Libro> findByAuthorHerrialdea(String herrialdea) {
        // Herrialdea bat kate bezala edo array modura egon daiteke.
        return libroCollection.find(
                or(
                        regex("author.herrialdea", herrialdea, "i"), // kate hutsa bada
                        elemMatch("author.herrialdea", regex(herrialdea, "i")) // array bada
                )).into(new ArrayList<>());
    }
}
