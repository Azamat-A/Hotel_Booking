package kg.megacom.hotel_booking.models.dtos;

import kg.megacom.hotel_booking.models.enums.EStatusBooking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    Long id;
    HotelDto hotel;
    RoomDto room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    UserDto guest;
    String comment;
    boolean active;
    float priceOfBook;

    EStatusBooking statusBooking;

}
