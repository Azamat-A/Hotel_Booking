package kg.megacom.hotel_booking.services;

import kg.megacom.hotel_booking.models.dtos.ReplyToReviewDto;
import org.springframework.http.ResponseEntity;

public interface ReplyToReviewService {
    ResponseEntity<?> save(ReplyToReviewDto replyToReviewDto);
    ResponseEntity<?> delete(ReplyToReviewDto replyToReviewDto);

}
