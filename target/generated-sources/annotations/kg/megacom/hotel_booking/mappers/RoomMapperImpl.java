package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.dtos.CityDto.CityDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.HotelDto.HotelDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.models.entities.City;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.entities.Room;
import kg.megacom.hotel_booking.models.entities.RoomCategory;
import kg.megacom.hotel_booking.models.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:14+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room roomDtoToRoom(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( roomDto.getId() );
        room.setCapacity( roomDto.getCapacity() );
        room.setBedType( roomDto.getBedType() );
        room.setSquare( roomDto.getSquare() );
        room.setWifi( roomDto.isWifi() );
        room.setHotel( hotelDtoToHotel( roomDto.getHotel() ) );
        room.setTypeOfView( roomDto.getTypeOfView() );
        room.setActive( roomDto.isActive() );
        room.setRoomCategory( roomCategoryDtoToRoomCategory( roomDto.getRoomCategory() ) );

        return room;
    }

    @Override
    public RoomDto roomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setId( room.getId() );
        roomDto.setCapacity( room.getCapacity() );
        roomDto.setBedType( room.getBedType() );
        roomDto.setSquare( room.getSquare() );
        roomDto.setWifi( room.isWifi() );
        roomDto.setHotel( hotelToHotelDto( room.getHotel() ) );
        roomDto.setTypeOfView( room.getTypeOfView() );
        roomDto.setActive( room.isActive() );
        roomDto.setRoomCategory( roomCategoryToRoomCategoryDto( room.getRoomCategory() ) );

        return roomDto;
    }

    @Override
    public List<Room> roomDtoListToRoomList(List<RoomDto> roomDtos) {
        if ( roomDtos == null ) {
            return null;
        }

        List<Room> list = new ArrayList<Room>( roomDtos.size() );
        for ( RoomDto roomDto : roomDtos ) {
            list.add( roomDtoToRoom( roomDto ) );
        }

        return list;
    }

    @Override
    public List<RoomDto> roomListToRoomDtoList(List<Room> rooms) {
        if ( rooms == null ) {
            return null;
        }

        List<RoomDto> list = new ArrayList<RoomDto>( rooms.size() );
        for ( Room room : rooms ) {
            list.add( roomToRoomDto( room ) );
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

    protected RoomCategory roomCategoryDtoToRoomCategory(RoomCategoryDto roomCategoryDto) {
        if ( roomCategoryDto == null ) {
            return null;
        }

        RoomCategory roomCategory = new RoomCategory();

        roomCategory.setId( roomCategoryDto.getId() );
        roomCategory.setTypeOfRoom( roomCategoryDto.getTypeOfRoom() );

        return roomCategory;
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

    protected RoomCategoryDto roomCategoryToRoomCategoryDto(RoomCategory roomCategory) {
        if ( roomCategory == null ) {
            return null;
        }

        RoomCategoryDto roomCategoryDto = new RoomCategoryDto();

        roomCategoryDto.setId( roomCategory.getId() );
        roomCategoryDto.setTypeOfRoom( roomCategory.getTypeOfRoom() );

        return roomCategoryDto;
    }
}
