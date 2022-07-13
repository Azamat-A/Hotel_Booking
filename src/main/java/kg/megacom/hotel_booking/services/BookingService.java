package kg.megacom.hotel_booking.services;

import kg.megacom.hotel_booking.models.dtos.BookingDto;
import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.request.CancelBooking;
import kg.megacom.hotel_booking.models.request.SaveBooking;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

   BookingDto save (BookingDto bookingDto);

    ResponseEntity<?> saveBooking(SaveBooking saveBooking);

    ResponseEntity<?> update(BookingDto bookingDto);

    ResponseEntity<?> delete(BookingDto bookingDto);

    BookingDto findById(Long id);

   ResponseEntity<?> cancelBooking(CancelBooking cancelBooking);
   
//   ResponseEntity<?> sendCode(String email);
//   ResponseEntity<?> sendCode2(String email);

   List<BookingDto> findAllByHotel(Long hotelId);
   List<BookingDto> findAllBooking(Long hotelId,int numberPerson,LocalDate checkInDate, LocalDate checkOutDate);

  List<BookingDto> findAllByRoomAndActive(RoomDto roomDto);



}
