package kg.megacom.hotel_booking.services;

import kg.megacom.hotel_booking.models.dtos.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDto save(UserDto userDto);
    ResponseEntity<?> update(UserDto userDto);
    ResponseEntity<?> delete(UserDto userDto);


    UserDto findById(Long id);
}
