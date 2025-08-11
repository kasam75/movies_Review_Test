package controller;

import dto.ReviewRequest;
import dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/movies{movieId}reviews")
    public ResponseEntity<ReviewResponse>
    saveReview(
            @RequestBody ReviewRequest request,
            @PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.save(request, movieId));
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>>
    getReviews(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.findAll(movieId));
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewResponse>
    getReview(@PathVariable Long reviewId) {
        return reviewService.findReview(reviewId);
    }

    @PutMapping("/reviews/{review}")
    public ResponseEntity<ReviewResponse>
    updateReview(
            @PathVariable Long review,
            @RequestBody ReviewRequest reviewRequest) {
        return reviewService.update(review, reviewRequest);
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(
            @PathVariable Long reviewId
    ) {
        reviewService.deleteReview(reviewId);
    }
}
