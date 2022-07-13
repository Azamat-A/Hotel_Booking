package kg.megacom.hotel_booking.models.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data

public class ReviewDto {

    Long id;
    UserDto user;
    HotelDto hotel;
    LocalDate date;
    Double score;
    String text;
    boolean active;
}
