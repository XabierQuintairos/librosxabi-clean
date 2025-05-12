package paagbi.librosxabi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import paagbi.librosxabi.model.Libro;
import paagbi.librosxabi.model.Rating;
import paagbi.librosxabi.model.Editorial;
import paagbi.librosxabi.repository.LibroRepository;
import paagbi.librosxabi.repository.RatingRepository;
import paagbi.librosxabi.repository.EditorialRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Operation(summary = "Liburu guztiak eskuratzen ditu")
    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Operation(summary = "ID bidez liburu bat eskuratzen du")
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return libroRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Liburu berri bat sortzen du")
    @PostMapping
    public ResponseEntity<Libro> createLibro(@Valid @RequestBody Libro libro) {
        try {
            Libro savedLibro = libroRepository.save(libro);
            return ResponseEntity.ok(savedLibro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "ID bidez liburu bat ezabatzen du")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibroById(@PathVariable String id) {
        try {
            if (libroRepository.findById(id).isPresent()) {
                libroRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "ID bidez liburu bat modifikatzen du")
    @PutMapping("/{id}")
    public ResponseEntity<Libro> modifyLibroById(@PathVariable String id, @Valid @RequestBody Libro libroRequest) {
        try {
            ObjectId objectId = new ObjectId(id);
            return libroRepository.findById(id).map(libro -> {
                libroRequest.setId(objectId);
                return ResponseEntity.ok(libroRepository.save(libroRequest));
            }).orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Liburuak bilatzen ditu generoaren arabera")
    @GetMapping("/searchByGeneres")
    public List<Libro> getLibrosByGeneres(@RequestParam String genero) {
        return libroRepository.findByGeneresContaining(genero);
    }

    @Operation(summary = "Liburuak bilatzen ditu gutxieneko balorazioaren arabera")
    @GetMapping("/searchByRating")
    public List<Libro> getLibrosByRating(@RequestParam double minRating) {
        return libroRepository.findByRatingGreaterThanEqual(minRating);
    }

    @Operation(summary = "Liburuak bilatzen ditu prezio tarte baten arabera")
    @GetMapping("/searchByPriceRange")
    public List<Libro> getLibrosByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return libroRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Operation(summary = "Liburuak bilatzen ditu hizkuntzaren arabera")
    @GetMapping("/searchByLanguage")
    public List<Libro> getLibrosByLanguage(@RequestParam String language) {
        return libroRepository.findByLanguage(language);
    }

    @Operation(summary = "Liburuak bilatzen ditu argitaletxearen arabera")
    @GetMapping("/searchByPublisher")
    public List<Libro> getLibrosByPublisher(@RequestParam String publisher) {
        return libroRepository.findByPublisher(publisher);
    }

    @Operation(summary = "Liburu guztien balorazioaren batez bestekoa kalkulatzen du")
    @GetMapping("/averageRating")
    public double getAverageRating() {
        return libroRepository.findAll().stream()
                .mapToDouble(Libro::getRating)
                .average()
                .orElse(0.0);
    }

    @Operation(summary = "Liburuak bilatzen ditu gutxieneko boto kopuruaren arabera")
    @GetMapping("/searchByVoters")
    public List<Libro> getLibrosByVoters(@RequestParam int minVoters) {
        return libroRepository.findByVotersGreaterThanEqual(minVoters);
    }

    @Operation(summary = "Liburuak bilatzen ditu autorearen izenaren arabera")
    @GetMapping("/searchByAuthorName")
    public List<Libro> getLibrosByAuthorName(@RequestParam String nombre) {
        return libroRepository.findByAuthorNombre(nombre);
    }

    @Operation(summary = "Liburuak bilatzen ditu autorearen herrialdearen arabera")
    @GetMapping("/searchByAuthorCountry")
    public List<Libro> getLibrosByAuthorCountry(@RequestParam String herrialdea) {
        return libroRepository.findByAuthorHerrialdea(herrialdea);
    }

    // berriak

    @Operation(summary = "Ratings guztiak bueltatzen ditu")
    @GetMapping("/ratings")
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Operation(summary = "Editorial guztiak bueltatzen ditu")
    @GetMapping("/editoriales")
    public List<Editorial> getEditoriales() {
        return editorialRepository.findAll();
    }
}
