package kg.megacom.hotel_booking.services;

import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.request.SaveCategoryAndPrice;
import org.springframework.http.ResponseEntity;

public interface RoomCategoryService {

    RoomCategoryDto save ( RoomCategoryDto roomCategoryDto);

    ResponseEntity<?> saveCategoryAndPrice(SaveCategoryAndPrice saveCategoryAndPrice);

    ResponseEntity<?> update(RoomCategoryDto roomCategoryDto);

    ResponseEntity<?> delete(RoomCategoryDto roomCategoryDto);

    RoomCategoryDto findById(Long roomCategoryId);
}
