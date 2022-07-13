package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.dtos.CityDto.CityDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.HotelDto.HotelDtoBuilder;
import kg.megacom.hotel_booking.models.dtos.ReviewDto;
import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.models.entities.City;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.entities.Review;
import kg.megacom.hotel_booking.models.entities.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:13+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review reviewDtoToReview(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review review = new Review();

        review.setId( reviewDto.getId() );
        review.setUser( userDtoToUser( reviewDto.getUser() ) );
        review.setHotel( hotelDtoToHotel( reviewDto.getHotel() ) );
        review.setDate( reviewDto.getDate() );
        review.setScore( reviewDto.getScore() );
        review.setText( reviewDto.getText() );
        review.setActive( reviewDto.isActive() );

        return review;
    }

    @Override
    public ReviewDto reviewToReviewDto(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId( review.getId() );
        reviewDto.setUser( userToUserDto( review.getUser() ) );
        reviewDto.setHotel( hotelToHotelDto( review.getHotel() ) );
        reviewDto.setDate( review.getDate() );
        reviewDto.setScore( review.getScore() );
        reviewDto.setText( review.getText() );
        reviewDto.setActive( review.isActive() );

        return reviewDto;
    }

    @Override
    public List<Review> reviewDtoListToReviewList(List<ReviewDto> reviewDtos) {
        if ( reviewDtos == null ) {
            return null;
        }

        List<Review> list = new ArrayList<Review>( reviewDtos.size() );
        for ( ReviewDto reviewDto : reviewDtos ) {
            list.add( reviewDtoToReview( reviewDto ) );
        }

        return list;
    }

    @Override
    public List<ReviewDto> reviewListToReviewDtoList(List<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewDto> list = new ArrayList<ReviewDto>( reviews.size() );
        for ( Review review : reviews ) {
            list.add( reviewToReviewDto( review ) );
        }

        return list;
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

    protected CityDto cityToCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDtoBuilder cityDto = CityDto.builder();

        cityDto.id( city.getId() );
        cityDto.name( city.getName() );

        return cityDto.build();
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
