package com.teamnathaniel.service;

import com.teamnathaniel.model.Review;
import com.teamnathaniel.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewService {
    ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review saveReview(Review review){
        return reviewRepository.save(review);
    }

    public Review findReviewById(int reviewId){
        return reviewRepository.findById(reviewId);
    }

    public void deleteReview(int reviewId){
        reviewRepository.deleteByReviewId(reviewId);
    }

    public Review updateReview(int reviewId, Review review){
        Review oldReview = reviewRepository.findById(reviewId);
        if(oldReview != null) {
            review.setReviewId(review.getReviewId());

            if (review.getReviewRating() != 0) {
                review.setReviewId(review.getReviewId());
            }
            if (review.getReviewDescription() != null) {
                review.setReviewDescription(review.getReviewDescription());
            }
        }
            return reviewRepository.save(review);
    }
}
