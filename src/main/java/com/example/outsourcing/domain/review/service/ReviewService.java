package com.example.outsourcing.domain.review.service;

import com.example.outsourcing.domain.review.dto.FindByStarDto;
import com.example.outsourcing.domain.store.entity.Store;
import com.example.outsourcing.domain.store.repository.StoreRepository;
import com.example.outsourcing.global.enums.OrderStatus;
import com.example.outsourcing.domain.member.entity.Member;
import com.example.outsourcing.domain.member.repository.MemberRepository;
import com.example.outsourcing.domain.order.entity.Order;
import com.example.outsourcing.domain.order.repository.OrderRepository;
import com.example.outsourcing.global.common.CommonResponseBody;
import com.example.outsourcing.domain.review.dto.ReviewRequestDto;
import com.example.outsourcing.domain.review.dto.ReviewResponseDto;
import com.example.outsourcing.domain.review.entity.Review;
import com.example.outsourcing.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public CommonResponseBody<ReviewResponseDto> createReview(Long orderId, ReviewRequestDto requestDto, Long loggedInUserId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("주문건을 찾을 수 없습니다."));
        if(!order.getStatus().equals(OrderStatus.DELIVERED)) {
            return new CommonResponseBody<>("배달이 완료되지 않았습니다", null);
        }
        Member member = memberRepository.findById(loggedInUserId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저아이디 입니다."));
        Store store = order.getStore();
        Review review = reviewRepository.save(requestDto.toEntity(member, store, order));

        return new CommonResponseBody<>("리뷰가 생성되었습니다.", ReviewResponseDto.toDto(review));
    }

    public Page<ReviewResponseDto> findReview(Long storeId, Long loggedInUserId, int page, int size) {
        Member member = validate(storeId, loggedInUserId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviews =reviewRepository.findByStoreId(storeId, member.getId(), pageable);

        return reviews.map(ReviewResponseDto::toDto);
    }

    public Page<ReviewResponseDto> findReviewByStar(Long storeId, Long loggedInUserId, int page, int size, FindByStarDto findByStarDto) {
        validate(storeId, loggedInUserId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "star"));
        Page<Review> reviews =reviewRepository.findByStarAndStoreId(storeId, findByStarDto.getMinStar(), findByStarDto.getMaxStar(), pageable);

        return reviews.map(ReviewResponseDto::toDto);
    }

    //
    public Member validate(Long storeId, Long loggedInUserId){
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));
        Order order = orderRepository.findByStoreId(storeId).orElseThrow(() -> new IllegalArgumentException("주문건을 찾을 수 없습니다."));
        Member member = memberRepository.findById(loggedInUserId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저아이디 입니다."));
        return member;
    }
}
