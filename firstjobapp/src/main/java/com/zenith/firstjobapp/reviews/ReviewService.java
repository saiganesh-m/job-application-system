package com.zenith.firstjobapp.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByCompanyId(Long companyId);
    Review getReviewById(Long companyId, Long reviewId);
    boolean addReview(Long companyId, Review review);
    boolean updateReview(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
