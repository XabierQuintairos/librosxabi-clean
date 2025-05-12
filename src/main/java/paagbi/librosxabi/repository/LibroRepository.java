package paagbi.librosxabi.repository;

import paagbi.librosxabi.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    List<Libro> findAll();
    Optional<Libro> findById(String id);
    Libro save(Libro libro);
    long deleteById(String id);
    List<Libro> findByGeneresContaining(String genero);
    List<Libro> findByRatingGreaterThanEqual(double rating);
    List<Libro> findByPriceBetween(double minPrice, double maxPrice);
    List<Libro> findByLanguage(String language);
    List<Libro> findByPublisher(String publisher);
    List<Libro> findByPageCountBetween(int minPages, int maxPages);
    List<Libro> findByVotersGreaterThanEqual(int minVoters);
    List<Libro> findByAuthorNombre(String nombre);
    List<Libro> findByAuthorHerrialdea(String herrialdea);
}