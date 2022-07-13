package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    Hotel hotelDtoToHotel(HotelDto hotelDto);

    HotelDto hotelToHotelDto(Hotel hotel);

    List<Hotel> hotelDtoListToHotelList(List<HotelDto> hotelDtos);

    List<HotelDto> hotelListToHotelDtoList(List<Hotel> hotels);
}
