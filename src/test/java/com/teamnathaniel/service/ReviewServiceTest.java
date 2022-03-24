package com.teamnathaniel.service;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.model.Review;
import com.teamnathaniel.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ReviewServiceTest {
    private ReviewService subject;

    private ReviewRepository rEpOsItOrY;

    private List<Review> allReviews;
    private Review aReview;

    private static boolean deleteCalled = false;

    @BeforeEach
    void setUp() {
        rEpOsItOrY = Mockito.mock(ReviewRepository.class);
        subject = new ReviewService(rEpOsItOrY);

        allReviews = new ArrayList<>();
        aReview = new Review();
        aReview.setReviewRating(5);
        aReview.setReviewDescription("The best");
        Customer customer = new Customer();
        customer.setName("Somebody");
        aReview.setCustomer(customer);
        allReviews.add(aReview);
    }

    @Test
    void getAllReviews() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allReviews);
        assertEquals(subject.getAllReviews(), allReviews);
    }

    @Test
    void saveReview() {
        Mockito.when(rEpOsItOrY.save(any(Review.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Review.class);
        });
        assertEquals(subject.saveReview(aReview), aReview);
    }

    @Test
    void findReviewById() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(aReview);
        assertEquals(subject.findReviewById(0), aReview);
    }

    @Test
    void deleteReview() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
             return null;
        }).when(rEpOsItOrY).deleteByReviewId(any(Integer.class));
        subject.deleteReview(0);
        assertTrue(deleteCalled);
    }

    @Test
    void updateReviewUpdatesInsteadOfCreating() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(aReview);
        Mockito.when(rEpOsItOrY.save(any(Review.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Review.class);
        });
        Review aSecondReview = new Review();
        aSecondReview.setReviewRating(2);
        aSecondReview.setReviewDescription("I don't like it");
        Customer customer = new Customer();
        customer.setName("Nobody");
        aSecondReview.setCustomer(customer);
        assertEquals(subject.updateReview(0, aSecondReview), aReview);
    }

    @Test
    void updateReviewNoPrev() {
        Mockito.when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(null);
        Mockito.when(rEpOsItOrY.save(any(Review.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Review.class);
        });
        assertEquals(subject.updateReview(0, aReview), aReview);
    }
}