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
public class BookingHistoryDto {
     Long id;
     BookingDto booking;
     LocalDate changeDate;
     String comment;
     RoomDto room;
     LocalDate  checkInDate;
     LocalDate checkOutDate;
     UserDto guest;
     UserDto user;
     boolean active;
     EStatusBooking statusBooking;
}
