package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.BookingHistoryDao;
import kg.megacom.hotel_booking.mappers.BookingHistoryMapper;
import kg.megacom.hotel_booking.models.dtos.BookingHistoryDto;
import kg.megacom.hotel_booking.models.entities.BookingHistory;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Autowired
    private  BookingHistoryDao bookingHistoryDao;
    private final BookingHistoryMapper bookingHistoryMapper = BookingHistoryMapper.Instance;

    @Override
    public ResponseEntity<?> save(BookingHistoryDto bookHistoryDto) {
        BookingHistory bookingHistory = bookingHistoryMapper.bookingHistoryDtoToBookingHistory(bookHistoryDto);
        bookingHistory.setActive(true);
        BookingHistory savedBookHistory = bookingHistoryDao.save(bookingHistory);
        return new ResponseEntity<>(savedBookHistory, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(BookingHistoryDto bookHistoryDto) {
        boolean isExists = bookingHistoryDao.existsById(bookHistoryDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("BookHistory is not found"), HttpStatus.NOT_FOUND);
        }else{
            BookingHistory bookHistory = bookingHistoryMapper.bookingHistoryDtoToBookingHistory(bookHistoryDto);
            BookingHistory updatedBookingHistory = bookingHistoryDao.save(bookHistory);
            return new ResponseEntity<>(updatedBookingHistory, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(BookingHistoryDto bookHistoryDto) {
        BookingHistory bookHistory = bookingHistoryMapper.bookingHistoryDtoToBookingHistory(bookHistoryDto);
        bookHistory.setActive(false);
        ResponseEntity<?> deletedBookHistory = update(bookingHistoryMapper.bookingHistoryToBookingHistoryDto(bookHistory));
        if (deletedBookHistory.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedBookHistory, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("BookHistory not deleted"), HttpStatus.NOT_FOUND);
        }
    }
}
