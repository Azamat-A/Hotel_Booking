package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.ReviewDto;
import kg.megacom.hotel_booking.models.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review reviewDtoToReview(ReviewDto reviewDto);

    ReviewDto reviewToReviewDto(Review review);

    List<Review> reviewDtoListToReviewList(List<ReviewDto> reviewDtos);

    List<ReviewDto> reviewListToReviewDtoList(List<Review> reviews);
}
