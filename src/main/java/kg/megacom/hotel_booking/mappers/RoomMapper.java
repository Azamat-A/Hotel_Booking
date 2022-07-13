package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room roomDtoToRoom(RoomDto roomDto);

    RoomDto roomToRoomDto(Room room);

    List<Room> roomDtoListToRoomList(List<RoomDto> roomDtos);

    List<RoomDto> roomListToRoomDtoList(List<Room> rooms);
}
