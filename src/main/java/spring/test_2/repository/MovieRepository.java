package spring.test_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.test_2.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
