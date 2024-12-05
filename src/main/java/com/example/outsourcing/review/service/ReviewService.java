package com.example.outsourcing.review.service;

import com.example.outsourcing.common.SuccessResponse;
import com.example.outsourcing.review.dto.ReviewRequestDto;
import com.example.outsourcing.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {


    public SuccessResponse<ReviewResponseDto> createReview(ReviewRequestDto requestDto) {



        return SuccessResponse.of("리뷰가 생성되었습니다.", null);
    }
}
