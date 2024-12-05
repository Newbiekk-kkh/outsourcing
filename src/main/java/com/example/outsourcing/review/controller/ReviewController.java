package com.example.outsourcing.review.controller;


import com.example.outsourcing.response.CommonResponseBody;
import com.example.outsourcing.review.dto.ReviewRequestDto;
import com.example.outsourcing.review.dto.ReviewResponseDto;
import com.example.outsourcing.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/reviews")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public CommonResponseBody<ReviewResponseDto> createReview(@RequestParam Long orderId, @RequestBody ReviewRequestDto requestDto, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("id");
        return reviewService.createReview(orderId, requestDto, loggedInUserId);
    }

}
