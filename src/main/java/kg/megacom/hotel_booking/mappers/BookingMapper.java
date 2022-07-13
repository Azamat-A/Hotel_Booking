package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.BookingDto;
import kg.megacom.hotel_booking.models.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking bookingDtoToBooking(BookingDto bookingDto);

    BookingDto bookingToBookingDto(Booking booking);

    List<Booking> bookingDtoListToBookingList(List<BookingDto> bookingDtos);

    List<BookingDto> bookingListToBookingDtoList(List<Booking> bookings);
}
