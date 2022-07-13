package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.ReplyToReviewDto;
import kg.megacom.hotel_booking.models.entities.ReplyToReview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReplyToReviewMapper {

    ReplyToReviewMapper INSTANCE = Mappers.getMapper(ReplyToReviewMapper.class);

    ReplyToReview replyToReviewDtoToReplyToReview(ReplyToReviewDto replyToReviewDto);

    ReplyToReviewDto replyToReviewToReplyToReviewDto(ReplyToReview replyToReview);

    List<ReplyToReview> replyToReviewDtoListToReplyToReviewList(List<ReplyToReviewDto> reviewDtos);

    List<ReplyToReviewDto> replyToReviewListToReplyToReviewDtoList(List<ReplyToReview> replyToReviews);
}
