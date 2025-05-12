package paagbi.librosxabi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import paagbi.librosxabi.model.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
}
