package spring.test_2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.test_2.dto.MovieRequest;
import spring.test_2.dto.MovieResponse;
import spring.test_2.service.MovieService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse>saveMovie
            (@RequestBody MovieRequest request){
        return ResponseEntity.ok(movieService.save(request));
    }
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }
    @GetMapping("/movies/{moviesId}")
    public ResponseEntity<MovieResponse> getMovie
            (@PathVariable Long moviesId) {
        return movieService.findmovie(moviesId);
    }
    @PutMapping("/movies/{moviesId}")
    public ResponseEntity<MovieResponse> updateMovie
            (@PathVariable Long moviesId,
             @RequestBody MovieRequest movieRequest) {
        return movieService.update(moviesId,movieRequest);
    }
    @DeleteMapping("/movies/{moviesId}")
    public void deleteMovie
            (@PathVariable Long moviesId) {
        movieService.deletMovies(moviesId);
    }

}
