package kg.megacom.hotel_booking.services;


import kg.megacom.hotel_booking.models.dtos.BookingHistoryDto;
import kg.megacom.hotel_booking.models.entities.BookingHistory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingHistoryService {
    ResponseEntity<?> save(BookingHistoryDto bookHistoryDto);
    ResponseEntity<?> update(BookingHistoryDto bookHistoryDto);

    ResponseEntity<?> delete(BookingHistoryDto bookHistoryDto);
}

