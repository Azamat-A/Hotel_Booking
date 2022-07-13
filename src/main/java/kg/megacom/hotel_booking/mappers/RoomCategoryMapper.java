package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.entities.RoomCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomCategoryMapper {

   RoomCategoryMapper INSTANCE = Mappers.getMapper(RoomCategoryMapper.class);

    RoomCategory roomCategoryDtoToRoomCategory(RoomCategoryDto roomCategoryDto);

    RoomCategoryDto roomCategoryToRoomCategoryDto(RoomCategory roomCategory);

    List<RoomCategory> roomCategoryDtoListToRoomCategoryList(List<RoomCategoryDto> roomCategoryDtos);

    List<RoomCategoryDto> roomCategoryListToRoomCategoryDtoList(List<RoomCategory> roomCategories);
}
