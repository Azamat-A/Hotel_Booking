package kg.megacom.hotel_booking.services;

import feign.Response;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.response.HotelFilterResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {

    ResponseEntity<?> save(HotelDto hotelDto);

    ResponseEntity<?> update(HotelDto hotelDto);

    ResponseEntity<?> delete(Long hotelId);

    HotelDto findById(Long hotelId);

    List<HotelDto> findAll();

    ResponseEntity<?> hotelBLock(Long hotelId);

    ResponseEntity<?> confirm(Long hotelId);


   ResponseEntity<?> Rating(List<HotelFilterResponse> hotelFilterResponsesList);

   List<HotelDto> findAllHotelsByCity(Long cityId);


    ResponseEntity<?> filter(Long cityId, LocalDate checkInDate, LocalDate checkOutDate,int guestAmount, EBedType bedType );


    void countCurrentScore();



}