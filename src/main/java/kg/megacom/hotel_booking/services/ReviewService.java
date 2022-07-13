package kg.megacom.hotel_booking.services;



import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.ReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<?> save(ReviewDto reviewDto);

    ResponseEntity<?> update(ReviewDto reviewDto);

    ResponseEntity<?> delete(ReviewDto reviewDto);

    List<ReviewDto> findAllByHotelAndActive(Long hotelId);
}
