package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.entities.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    
    City cityDtoToCity(CityDto cityDto);
    
    CityDto cityToCityDto(City city);

    List<City> cityDtoListToCityList(List<CityDto> cityDtos);

    List<CityDto> cityListToCityDtoList(List<City> cities);
}
