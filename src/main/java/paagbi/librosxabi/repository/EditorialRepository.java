package paagbi.librosxabi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import paagbi.librosxabi.model.Editorial;

public interface EditorialRepository extends MongoRepository<Editorial, String> {
}
