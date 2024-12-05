package com.example.outsourcing.review.controller;


import com.example.outsourcing.common.SuccessResponse;
import com.example.outsourcing.review.dto.ReviewRequestDto;
import com.example.outsourcing.review.dto.ReviewResponseDto;
import com.example.outsourcing.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/reviews")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public SuccessResponse<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto requestDto, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("id");
        return reviewService.createReview(requestDto);
    }

}
