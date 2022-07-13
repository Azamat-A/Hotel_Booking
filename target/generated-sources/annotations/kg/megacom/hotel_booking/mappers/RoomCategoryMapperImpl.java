package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.entities.RoomCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:14+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class RoomCategoryMapperImpl implements RoomCategoryMapper {

    @Override
    public RoomCategory roomCategoryDtoToRoomCategory(RoomCategoryDto roomCategoryDto) {
        if ( roomCategoryDto == null ) {
            return null;
        }

        RoomCategory roomCategory = new RoomCategory();

        roomCategory.setId( roomCategoryDto.getId() );
        roomCategory.setTypeOfRoom( roomCategoryDto.getTypeOfRoom() );

        return roomCategory;
    }

    @Override
    public RoomCategoryDto roomCategoryToRoomCategoryDto(RoomCategory roomCategory) {
        if ( roomCategory == null ) {
            return null;
        }

        RoomCategoryDto roomCategoryDto = new RoomCategoryDto();

        roomCategoryDto.setId( roomCategory.getId() );
        roomCategoryDto.setTypeOfRoom( roomCategory.getTypeOfRoom() );

        return roomCategoryDto;
    }

    @Override
    public List<RoomCategory> roomCategoryDtoListToRoomCategoryList(List<RoomCategoryDto> roomCategoryDtos) {
        if ( roomCategoryDtos == null ) {
            return null;
        }

        List<RoomCategory> list = new ArrayList<RoomCategory>( roomCategoryDtos.size() );
        for ( RoomCategoryDto roomCategoryDto : roomCategoryDtos ) {
            list.add( roomCategoryDtoToRoomCategory( roomCategoryDto ) );
        }

        return list;
    }

    @Override
    public List<RoomCategoryDto> roomCategoryListToRoomCategoryDtoList(List<RoomCategory> roomCategories) {
        if ( roomCategories == null ) {
            return null;
        }

        List<RoomCategoryDto> list = new ArrayList<RoomCategoryDto>( roomCategories.size() );
        for ( RoomCategory roomCategory : roomCategories ) {
            list.add( roomCategoryToRoomCategoryDto( roomCategory ) );
        }

        return list;
    }
}
