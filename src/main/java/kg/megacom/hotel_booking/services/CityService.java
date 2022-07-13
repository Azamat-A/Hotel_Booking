package kg.megacom.hotel_booking.services;

import kg.megacom.hotel_booking.models.dtos.CityDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {

    ResponseEntity<?> save(CityDto cityDto);
    ResponseEntity<?> update(CityDto cityDto);
    ResponseEntity<?> delete(CityDto cityDto);
    CityDto findById(Long id);


    ResponseEntity<?>findAll();
}
