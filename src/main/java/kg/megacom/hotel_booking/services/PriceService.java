package kg.megacom.hotel_booking.services;

import kg.megacom.hotel_booking.models.dtos.PriceDto;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.entities.Price;
import kg.megacom.hotel_booking.models.entities.Room;
import kg.megacom.hotel_booking.models.entities.RoomCategory;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

    PriceDto save (PriceDto priceDto);
    ResponseEntity<?> update(PriceDto priceDto);
    ResponseEntity<?> delete(PriceDto priceDto);

    PriceDto findPrice(RoomCategoryDto roomCategoryDto, LocalDate date);
}
