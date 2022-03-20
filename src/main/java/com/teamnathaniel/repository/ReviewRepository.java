package com.teamnathaniel.repository;

import com.teamnathaniel.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("From Review")
    List<Review> findAll();

    @Query("from Review where reviewId = :id")
    Review findById(int id);

    Review save(Review review);

    void deleteByReviewId(int reviewId);
}
