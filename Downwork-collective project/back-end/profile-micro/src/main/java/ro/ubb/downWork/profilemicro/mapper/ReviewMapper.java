package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.ReviewDto;
import ro.ubb.downWork.profilemicro.dto.ReviewDtoList;
import ro.ubb.downWork.profilemicro.model.Review;

/**
 * Created by langchristian96 on 1/19/2018.
 */
@Service
public class ReviewMapper extends AbstractMapper<Review, ReviewDto>  {
    @Override
    public Review toInternal(ReviewDto reviewDto) {
        return Review.builder().message(reviewDto.getMessage()).reviewerUsername(reviewDto.getReviewer()).positive(reviewDto.isPositive()).build();
    }

    @Override
    public ReviewDto toExternal(Review review) {
        return new ReviewDto(review.getMessage(), review.isPositive(), review.getReviewerUsername());
    }
}