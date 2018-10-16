package ro.ubb.downWork.profilemicro.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.downWork.profilemicro.model.Review;

/**
 * Created by langchristian96 on 1/19/2018.
 */

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
