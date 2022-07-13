package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.dtos.CityDto.CityDtoBuilder;
import kg.megacom.hotel_booking.models.entities.City;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:14+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class CityMapperImpl implements CityMapper {

    @Override
    public City cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        city.setId( cityDto.getId() );
        city.setName( cityDto.getName() );
        city.setActive( cityDto.isActive() );

        return city;
    }

    @Override
    public CityDto cityToCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDtoBuilder cityDto = CityDto.builder();

        cityDto.id( city.getId() );
        cityDto.name( city.getName() );

        return cityDto.build();
    }

    @Override
    public List<City> cityDtoListToCityList(List<CityDto> cityDtos) {
        if ( cityDtos == null ) {
            return null;
        }

        List<City> list = new ArrayList<City>( cityDtos.size() );
        for ( CityDto cityDto : cityDtos ) {
            list.add( cityDtoToCity( cityDto ) );
        }

        return list;
    }

    @Override
    public List<CityDto> cityListToCityDtoList(List<City> cities) {
        if ( cities == null ) {
            return null;
        }

        List<CityDto> list = new ArrayList<CityDto>( cities.size() );
        for ( City city : cities ) {
            list.add( cityToCityDto( city ) );
        }

        return list;
    }
}
