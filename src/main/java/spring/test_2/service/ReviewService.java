package spring.test_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.test_2.dto.ReviewRequest;
import spring.test_2.dto.ReviewResponse;
import spring.test_2.entity.Movie;
import spring.test_2.entity.Review;
import spring.test_2.repository.MovieRepository;
import spring.test_2.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    public final ReviewRepository reviewRepository;
    public final MovieRepository movieRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 번호의 무비는 없습니다")
        );
        Review review = new Review(
                request.getContent(),
                movie
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 번호의 무비는 없습니다")
        );
        List<Review> movies = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();
        for (Review review : movies) {
            dtos.add(new ReviewResponse(
                    review.getId(),
                    review.getContent()
            ));
        }
        return dtos;
    }

    @Transactional
    public ResponseEntity<ReviewResponse> update(Long moviesId, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(moviesId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디의 무비는 없습니다."));
        review.updateContent(reviewRequest.getContent());
        ReviewResponse response = new ReviewResponse(
                review.getId(),
                review.getContent()//여기 위에 왜 바로 리턴으로 리턴 안할까?
        );
        return ResponseEntity.ok(response);
    }


    @Transactional
    public void deleteReview(Long moviesId) {
        reviewRepository.deleteById(moviesId);
        boolean b = reviewRepository.existsById(moviesId);
        if (!b) {
            throw new IllegalArgumentException("그런 아이디의 무비는 없습니다");
        }
        movieRepository.deleteById(moviesId);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ReviewResponse> findReview(Long moviesId) {
        Movie movie = movieRepository.findById(moviesId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디의 무비는 없습니다")
        );
        new ReviewResponse(movie.getId(), movie.getTitle());
        return ResponseEntity.ok(new ReviewResponse(
                movie.getId(),
                movie.getTitle()));
    }
}
