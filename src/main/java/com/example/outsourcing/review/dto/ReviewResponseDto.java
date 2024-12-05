package com.example.outsourcing.review.dto;

import com.example.outsourcing.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewResponseDto {
    private int star;
    private String review;

    public static ReviewResponseDto toDto(Review review) {
        return ReviewResponseDto.builder()
                .review(review.getReview())
                .star(review.getStar())
                .build();
    }
}
