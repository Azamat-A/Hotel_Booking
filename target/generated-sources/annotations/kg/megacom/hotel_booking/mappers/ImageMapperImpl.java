package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.dtos.CityDto.CityDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.HotelDto.HotelDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.ImageDto;
import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.models.entities.City;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.entities.Image;
import kg.megacom.hotel_booking.models.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:14+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class ImageMapperImpl implements ImageMapper {

    @Override
    public Image imageDtoToImage(ImageDto imageDto) {
        if ( imageDto == null ) {
            return null;
        }

        Image image = new Image();

        image.setId( imageDto.getId() );
        image.setLink( imageDto.getLink() );
        image.setPosition( imageDto.getPosition() );
        image.setHotel( hotelDtoToHotel( imageDto.getHotel() ) );

        return image;
    }

    @Override
    public ImageDto imageToImageDto(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDto imageDto = new ImageDto();

        imageDto.setId( image.getId() );
        imageDto.setLink( image.getLink() );
        imageDto.setPosition( image.getPosition() );
        imageDto.setHotel( hotelToHotelDto( image.getHotel() ) );

        return imageDto;
    }

    @Override
    public List<Image> imageDtoListToImageList(List<ImageDto> imageDtos) {
        if ( imageDtos == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( imageDtos.size() );
        for ( ImageDto imageDto : imageDtos ) {
            list.add( imageDtoToImage( imageDto ) );
        }

        return list;
    }

    @Override
    public List<ImageDto> imageListToImageDtoList(List<Image> images) {
        if ( images == null ) {
            return null;
        }

        List<ImageDto> list = new ArrayList<ImageDto>( images.size() );
        for ( Image image : images ) {
            list.add( imageToImageDto( image ) );
        }

        return list;
    }

    protected City cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        city.setId( cityDto.getId() );
        city.setName( cityDto.getName() );
        city.setActive( cityDto.isActive() );

        return city;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        user.setRole( userDto.getRole() );
        user.setActive( userDto.isActive() );

        return user;
    }

    protected Hotel hotelDtoToHotel(HotelDto hotelDto) {
        if ( hotelDto == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setId( hotelDto.getId() );
        hotel.setName( hotelDto.getName() );
        hotel.setDescription( hotelDto.getDescription() );
        hotel.setAddress( hotelDto.getAddress() );
        hotel.setStar( hotelDto.getStar() );
        hotel.setPhone( hotelDto.getPhone() );
        hotel.setCurrentScore( hotelDto.getCurrentScore() );
        hotel.setEmail( hotelDto.getEmail() );
        hotel.setCity( cityDtoToCity( hotelDto.getCity() ) );
        hotel.setHotelStatus( hotelDto.getHotelStatus() );
        hotel.setManager( userDtoToUser( hotelDto.getManager() ) );

        return hotel;
    }

    protected CityDto cityToCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDtoBuilder cityDto = CityDto.builder();

        cityDto.id( city.getId() );
        cityDto.name( city.getName() );

        return cityDto.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setRole( user.getRole() );
        userDto.setActive( user.isActive() );

        return userDto;
    }

    protected HotelDto hotelToHotelDto(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        HotelDtoBuilder hotelDto = HotelDto.builder();

        hotelDto.id( hotel.getId() );
        hotelDto.name( hotel.getName() );
        hotelDto.description( hotel.getDescription() );
        hotelDto.address( hotel.getAddress() );
        hotelDto.star( hotel.getStar() );
        hotelDto.phone( hotel.getPhone() );
        hotelDto.currentScore( hotel.getCurrentScore() );
        hotelDto.email( hotel.getEmail() );
        hotelDto.city( cityToCityDto( hotel.getCity() ) );
        hotelDto.hotelStatus( hotel.getHotelStatus() );
        hotelDto.manager( userToUserDto( hotel.getManager() ) );

        return hotelDto.build();
    }
}
