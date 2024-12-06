package com.example.outsourcing.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class FindByStarDto {

    @Min(1)
    @Max(4)
    private int minStar;

    @Min(2)
    @Max(5)
    private int maxStar;


}
