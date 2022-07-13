package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.RoomDao;
import kg.megacom.hotel_booking.exceptions.RoomException;
import kg.megacom.hotel_booking.mappers.HotelMapper;
import kg.megacom.hotel_booking.mappers.RoomMapper;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.entities.Room;
import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.request.SaveRoom;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.HotelService;
import kg.megacom.hotel_booking.services.PriceService;
import kg.megacom.hotel_booking.services.RoomCategoryService;
import kg.megacom.hotel_booking.services.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired private RoomDao roomDao;
    @Autowired private HotelService hotelService;

   @Autowired private RoomCategoryService roomCategoryService;

  private  final  HotelMapper hotelMapper = HotelMapper.INSTANCE;

  private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = roomMapper.roomDtoToRoom(roomDto);
        Room roomSaved = roomDao.save(room);
        return roomMapper.roomToRoomDto(roomSaved);
    }

    @Override
    public ResponseEntity<?> saveRoom(SaveRoom saveRoom) throws RoomException {
        try{
            HotelDto hotelDto = hotelService.findById(saveRoom.getHotelId());
            RoomCategoryDto roomCategoryDto = roomCategoryService.findById(saveRoom.getRoomCategoryId());

            RoomDto room = new RoomDto();
            room.setCapacity(saveRoom.getCapacity());
            room.setBedType(saveRoom.getBedType());
            room.setSquare(saveRoom.getSquare());
            room.setWifi(saveRoom.isWifi());
            room.setHotel(hotelDto);
            room.setActive(true);
            room.setTypeOfView(saveRoom.getTypeOfView());
            room.setRoomCategory(roomCategoryDto);
            RoomDto savedRoom = save(room);

            return new ResponseEntity<>(savedRoom,HttpStatus.OK);
        }catch (RoomException r){
            log.error("Failed while saving room(method: saveRoom)");
            RoomException roomException = new RoomException("Failed while saving room(method: saveRoom");
            return new ResponseEntity<>(roomException.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public ResponseEntity<?> update(RoomDto roomDto) {
        boolean isExists = roomDao.existsById(roomDto.getId());
        if(!isExists){
            return  new ResponseEntity<>(Message.of("Room not found"), HttpStatus.NOT_FOUND);
        }else {
            Room room = roomMapper.roomDtoToRoom(roomDto);
            Room updatedRoom = roomDao.save(room);
            return new ResponseEntity<>(updatedRoom,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(RoomDto roomDto) {
        Room room = roomMapper.roomDtoToRoom(roomDto);
        room.setActive(false);
        ResponseEntity<?> deledRoom = update(roomMapper.roomToRoomDto(room));
        if(deledRoom.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(Message.of("Room is deleted"),HttpStatus.OK);

        }else {
            return new ResponseEntity<>(Message.of("Room not deleted"),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public RoomDto findById(Long roomId) {
        Room room = roomDao.findById(roomId).orElse(null);
        if(room == null) log.error("Room not found from database:  " + room);
        log.info("Room successfully found from database:  " + room);
        return roomMapper.roomToRoomDto(room);
    }

    @Override
    public List<RoomDto> findAllRoomsByHotel(Hotel hotel, EBedType bedType, int capacity) {
        List<Room> rooms = roomDao.findAllByActiveTrueAndHotelAndBedTypeAndCapacity(hotel, bedType,capacity);
        return roomMapper.roomListToRoomDtoList(rooms);
    }

}
