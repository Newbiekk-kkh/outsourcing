package com.example.outsourcing.domain.review.repository;

import com.example.outsourcing.domain.review.entity.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select rv from Review rv where rv.store.id=:storeId and rv.member.id!=:memberId")
    Page<Review> findByStoreId(@Param("storeId") Long storeId,@Param("memberId") Long memberId, Pageable pageable);

    @Query("select rv from Review rv where rv.store.id=:storeId and rv.star between :minStar and :maxStar")
    Page<Review> findByStarAndStoreId(@Param("storeId")Long storeId, @Param("minStar")int minStar, @Param("maxStar")int maxStar, Pageable pageable);

}
