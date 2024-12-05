package com.example.outsourcing.domain.review.controller;


import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.domain.review.dto.ReviewRequestDto;
import com.example.outsourcing.domain.review.dto.ReviewResponseDto;
import com.example.outsourcing.domain.review.service.ReviewService;
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
