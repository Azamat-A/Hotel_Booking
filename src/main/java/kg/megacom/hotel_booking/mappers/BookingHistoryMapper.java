package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.BookingDto;
import kg.megacom.hotel_booking.models.dtos.BookingHistoryDto;
import kg.megacom.hotel_booking.models.entities.BookingHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookingHistoryMapper {
    BookingHistoryMapper Instance = Mappers.getMapper(BookingHistoryMapper.class);

    BookingHistory bookingHistoryDtoToBookingHistory(BookingHistoryDto bookingHistoryDto);

    BookingHistoryDto bookingHistoryToBookingHistoryDto(BookingHistory bookingHistory);

    List<BookingHistory> bookingHistoryDtoToBookingList(List<BookingHistoryDto> bookingHistoryDtos);

    List<BookingHistoryDto> bookingHistoryListToBookingHistoryDtoList(List<BookingHistory> bookingHistories);
}
