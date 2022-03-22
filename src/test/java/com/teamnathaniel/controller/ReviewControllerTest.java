package com.teamnathaniel.controller;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.model.Review;
import com.teamnathaniel.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService sErViCe;

    private List<Review> reviews;

    private Review aReview;

    private static boolean deleteCalled = false;

    @BeforeEach
    void setUp() {
        reviews = new ArrayList<>();
        aReview = new Review();
        aReview.setReviewRating(5);
        aReview.setReviewDescription("Wow that was so good!");
        Customer reviewer = new Customer();
        reviewer.setName("Reviewy McReviewer");
        aReview.setCustomer(reviewer);
        reviews.add(aReview);
    }

    @Test
    void getAllReviews() throws Exception {
        Mockito.when(sErViCe.getAllReviews()).thenReturn(reviews);
        this.mockMvc.perform(get("/getAllReviews"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"reviewRating\": 5, \"reviewDescription\": \"Wow that was so good!\", \"customer\": { \"name\": \"Reviewy McReviewer\" }}]"));
    }

    @Test
    void createReview() throws Exception {
        Mockito.when(sErViCe.saveReview(any(Review.class))).thenReturn(aReview);
        this.mockMvc.perform(post("/saveReview")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"reviewDescription\": \"Great!\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"reviewRating\": 5, \"reviewDescription\": \"Wow that was so good!\", \"customer\": { \"name\": \"Reviewy McReviewer\" } }"));
    }

    @Test
    void findReviewById() throws Exception {
        Mockito.when(sErViCe.findReviewById(any(Integer.class))).thenReturn(aReview);
        this.mockMvc.perform(get("/findReview/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"reviewRating\": 5, \"reviewDescription\": \"Wow that was so good!\", \"customer\": { \"name\": \"Reviewy McReviewer\" } }"));
    }

    @Test
    void deleteReview() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deleteReview(any(Integer.class));
        this.mockMvc.perform(delete("/deleteReview/0"))
                .andDo(print())
                .andExpect(status().isOk());
        assertTrue(deleteCalled);
    }

    @Test
    void updateReview() throws Exception {
        Mockito.when(sErViCe.updateReview(any(Integer.class), any(Review.class))).thenReturn(aReview);
        this.mockMvc.perform(put("/updateReview/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"reviewRating\": 5, \"reviewDescription\": \"Wow that was so good!\", \"customer\": { \"name\": \"Reviewy McReviewer\" } }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"reviewRating\": 5, \"reviewDescription\": \"Wow that was so good!\", \"customer\": { \"name\": \"Reviewy McReviewer\" } }"));
    }
}