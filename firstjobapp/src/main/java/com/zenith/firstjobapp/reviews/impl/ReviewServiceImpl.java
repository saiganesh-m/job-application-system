package com.zenith.firstjobapp.reviews.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenith.firstjobapp.company.Company;
import com.zenith.firstjobapp.company.CompanyService;
import com.zenith.firstjobapp.reviews.Review;
import com.zenith.firstjobapp.reviews.ReviewRepository;
import com.zenith.firstjobapp.reviews.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        // Review review = reviewRepository.findById(reviewId).orElse(null);
         // return (review != null && review.getCompany().getId().equals(companyId)) ? review : null;
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                      .filter(review -> review.getId().equals(reviewId))
                      .findFirst()
                      .orElse(null);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        // if (review.getCompany() != null && review.getCompany().getId().equals(companyId)) {
        //     reviewRepository.save(review);
        //     return true;
        // }
        // return false;
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        // Review existingReview = getReviewById(companyId, reviewId);
        // if (existingReview != null) {
        //     existingReview.setTitle(review.getTitle());
        //     existingReview.setDescription(review.getDescription());
        //     existingReview.setRating(review.getRating());
        //     reviewRepository.save(existingReview);
        //     return true;
        // }
        // return false;
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        // Review existingReview = getReviewById(companyId, reviewId);
        // if (existingReview != null) {
        //     reviewRepository.delete(existingReview);
        //     return true;
        // }
        // return false;
        if (companyService.getCompanyById(companyId) !=  null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyById(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
    
}
