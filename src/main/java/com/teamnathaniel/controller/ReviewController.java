package com.teamnathaniel.controller;

import com.teamnathaniel.model.Review;
import com.teamnathaniel.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @CrossOrigin
    @GetMapping("getAllReviews")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @CrossOrigin
    @PostMapping("saveReview")
    public Review createReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }

    @CrossOrigin
    @GetMapping("findReview/{id}")
    public Review findReviewById(@PathVariable int id){
        return reviewService.findReviewById(id);
    }

    @CrossOrigin
    @DeleteMapping("deleteReview/{reviewId}")
    public boolean deleteReview(@PathVariable int reviewId){
        reviewService.deleteReview(reviewId);
        System.out.println("Review with id" + reviewId + " was deleted.");
        return true;
    }

    @CrossOrigin
    @PutMapping("updateReview/{reviewId}")
    public Review updateReview(@PathVariable int reviewId, @RequestBody Review review){
        return reviewService.updateReview(reviewId, review);
    }
}
