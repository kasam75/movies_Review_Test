package controller;

import dto.MovieRequest;
import dto.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MovieService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie
            (@RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.save(request));
    }
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }
    @GetMapping("/movies/{moviesId}")
    public ResponseEntity<MovieResponse> getMovie
            (@PathVariable Long moviesId) {
        return movieService.findmovie(moviesId);
    }//영화 단건 조회 컨트롤러
    @PutMapping("/movies/{moviesId}")
    public ResponseEntity<MovieResponse> updateMovie
            (@PathVariable Long moviesId,
             @RequestBody MovieRequest movieRequest) {
        return movieService.update(moviesId,movieRequest);
    }//패치 (업데이트)컨트롤러
    @DeleteMapping("/movies/{moviesId}")
    public void deleteMovie
            (@PathVariable Long moviesId) {
        movieService.deletMovies(moviesId);
    }
    //저번에 자꾸 실수한 딜리트 추가해봄 딜리트는 보이드를 추가하면 리스폰스 엔티티가 안됨기존방식으로 진행해 보겟음

}
