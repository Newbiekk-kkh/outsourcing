package com.example.outsourcing.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class FindByStarDto {

    @Min(1)
    @Max(5)
    private int minStar;

    @Min(1)
    @Max(5)
    private int maxStar;
}
