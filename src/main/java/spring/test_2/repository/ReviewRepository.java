package spring.test_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.test_2.entity.Movie;
import spring.test_2.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByMovie(Movie movie);
}
