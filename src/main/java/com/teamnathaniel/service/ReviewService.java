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

            //   TODO A zero rating is valid, but we don't want to force api consumers to supply a rating every time
            //    Maybe change the type of Rating from int to Integer?
            oldReview.setReviewRating(review.getReviewRating());

            if (review.getReviewDescription() != null) {
                oldReview.setReviewDescription(review.getReviewDescription());
            }
            if (review.getCustomer() != null) {
                oldReview.setCustomer(review.getCustomer());
            }
            return reviewRepository.save(oldReview);
        }
            return reviewRepository.save(review);
    }
}
