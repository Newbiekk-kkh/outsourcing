package com.example.outsourcing.domain.review.service;

import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.global.enums.OrdersStatus;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import com.example.outsourcing.domain.orders.entity.Orders;
import com.example.outsourcing.domain.orders.repository.OrdersRepository;
import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.domain.review.dto.ReviewRequestDto;
import com.example.outsourcing.domain.review.dto.ReviewResponseDto;
import com.example.outsourcing.domain.review.entity.Review;
import com.example.outsourcing.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    //삭제된 memberId 가져오는거 예외 처리


    private final ReviewRepository reviewRepository;
    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;

    public CommonResponseBody<ReviewResponseDto> createReview(Long orderId, ReviewRequestDto requestDto, Long loggedInUserId) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("주문건을 찾을 수 없습니다."));
        if(!orders.getStatus().equals(OrdersStatus.DELIVERED)) {
            return new CommonResponseBody<>("배달이 완료되지 않았습니다", null);
        }
        Member member = memberRepository.findById(loggedInUserId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저아이디 입니다."));
        Store store = orders.getStore();
        Review review = reviewRepository.save(requestDto.toEntity(member, store, orders));

        return new CommonResponseBody<>("리뷰가 생성되었습니다.", ReviewResponseDto.toDto(review));
    }
}
