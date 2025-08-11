package spring.test_2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.test_2.dto.ReviewRequest;
import spring.test_2.dto.ReviewResponse;
import spring.test_2.service.ReviewService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview
            (@RequestBody ReviewRequest request,
             @PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.save(request, movieId));
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews
            (@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.findAll(movieId));
    }

    @GetMapping("/movies/{movieId}/{reviewId}")
    public ResponseEntity<ReviewResponse>
    getReview(@PathVariable Long reviewId) {
        return reviewService.findReview(reviewId);
    }

    @PutMapping("/movies/{movieId}/{reviewId}")
    public ResponseEntity<ReviewResponse>
    updateReview(
            @PathVariable Long review,
            @RequestBody ReviewRequest reviewRequest) {
        return reviewService.update(review, reviewRequest);
    }

    @DeleteMapping("/movies/{movieId}/{reviewId}")
    public void deleteReview(
            @PathVariable Long reviewId
    ) {
        reviewService.deleteReview(reviewId);
    }
}
