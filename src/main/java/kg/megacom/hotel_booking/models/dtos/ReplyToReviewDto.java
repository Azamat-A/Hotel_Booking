package kg.megacom.hotel_booking.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyToReviewDto {

    Long id;
    String text;
    UserDto user;
    LocalDate localDate;
    ReviewDto reviewDto;
}
