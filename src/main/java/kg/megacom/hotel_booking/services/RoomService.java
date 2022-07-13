package kg.megacom.hotel_booking.services;


import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.request.SaveRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {
    RoomDto save(RoomDto roomDto);
    ResponseEntity<?> saveRoom(SaveRoom saveRoom);
    ResponseEntity<?> update(RoomDto roomDto);
    ResponseEntity<?> delete(RoomDto roomDto);

    RoomDto findById(Long roomId);
    List<RoomDto> findAllRoomsByHotel(Hotel hotel, EBedType bedType,int capacity);
}
