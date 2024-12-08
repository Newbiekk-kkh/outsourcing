package com.example.outsourcing.domain.review.controller;


import com.example.outsourcing.domain.review.dto.FindByStarDto;
import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.domain.review.dto.ReviewRequestDto;
import com.example.outsourcing.domain.review.dto.ReviewResponseDto;
import com.example.outsourcing.domain.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/reviews")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping
    public CommonResponseBody<ReviewResponseDto> createReview(@RequestParam Long orderId, @RequestBody ReviewRequestDto requestDto, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("id");
        return reviewService.createReview(orderId, requestDto, loggedInUserId);
    }

    //리뷰 조회
    @GetMapping
    public CommonResponseBody<Page<ReviewResponseDto>> findReview(
            @RequestParam Long storeId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size,
            HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("id");
        Page<ReviewResponseDto> reviewResponse = reviewService.findReview(storeId,loggedInUserId, page, size);
        return new CommonResponseBody<>("가게에 대한 리뷰입니다.", reviewResponse);
    }

    //
    @GetMapping("/star")
    public CommonResponseBody<Page<ReviewResponseDto>> findStarReview(
            @RequestParam Long storeId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size,
            @Valid @RequestBody FindByStarDto findByStarDto,
            HttpSession session
    ){
        Long loggedInUserId = (Long) session.getAttribute("id");
        Page<ReviewResponseDto> reviewResponse = reviewService.findReviewByStar(storeId,loggedInUserId, page, size, findByStarDto);

        return new CommonResponseBody<>("별점에 따른 리뷰입니다.", reviewResponse);
    }

}
