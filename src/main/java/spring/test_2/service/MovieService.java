package spring.test_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.test_2.dto.MovieRequest;
import spring.test_2.dto.MovieResponse;
import spring.test_2.entity.Movie;
import spring.test_2.repository.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    public MovieResponse save(MovieRequest request) {
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle());
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movie -> new MovieResponse(
                movie.getId(),
                movie.getTitle()
        )).toList();
    }

    @Transactional
    public ResponseEntity<MovieResponse> update(Long moviesId, MovieRequest moviesRequest) {
        Movie movie = movieRepository.findById(moviesId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디의 무비는 없습니다")
        );
        movie.updateTitle(moviesRequest.getTitle());
        MovieResponse response = new MovieResponse(
                movie.getId(),
                movie.getTitle()//여기 위에 왜 바로 리턴으로 리턴 안할까?
        );
        return ResponseEntity.ok(response);
    }


    @Transactional
    public void deletMovies(Long moviesId) {
        movieRepository.deleteById(moviesId);
        boolean b = movieRepository.existsById(moviesId);
        if (!b) {
            throw new IllegalArgumentException("그런 아이디의 무비는 없습니다");
        }
        movieRepository.deleteById(moviesId);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<MovieResponse> findmovie(Long moviesId) {
        Movie movie = movieRepository.findById(moviesId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디의 무비는 없습니다")
        );
        new MovieResponse(movie.getId(), movie.getTitle());
        return ResponseEntity.ok(new MovieResponse(
                movie.getId(),
                movie.getTitle()
        ));
    }
}
