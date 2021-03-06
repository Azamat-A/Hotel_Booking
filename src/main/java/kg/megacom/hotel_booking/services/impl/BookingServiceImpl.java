package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.conifguration.EmailSender;
import kg.megacom.hotel_booking.dao.BookingDao;
import kg.megacom.hotel_booking.exceptions.BookingException;
import kg.megacom.hotel_booking.exceptions.CancelBookingErrorException;
import kg.megacom.hotel_booking.mappers.BookingMapper;
import kg.megacom.hotel_booking.mappers.HotelMapper;
import kg.megacom.hotel_booking.mappers.RoomMapper;
import kg.megacom.hotel_booking.mappers.UserMapper;
import kg.megacom.hotel_booking.models.dtos.*;
import kg.megacom.hotel_booking.models.entities.Booking;
import kg.megacom.hotel_booking.models.enums.EStatusBooking;
import kg.megacom.hotel_booking.models.request.CancelBooking;
import kg.megacom.hotel_booking.models.request.SaveBooking;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingHistoryService bookingHistoryService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    private HotelMapper hotelMapper = HotelMapper.INSTANCE;
    private RoomMapper roomMapper = RoomMapper.INSTANCE;
    private UserMapper userMapper = UserMapper.INSTANCE;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Override
    @Transactional
    public BookingDto save( BookingDto bookingDto) throws BookingException {

        try {
            Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
            booking.setStatusBooking(EStatusBooking.ACTIVE);

            Booking bookingSaved = bookingDao.save(booking);

            BookingHistoryDto bookHistory = new BookingHistoryDto();

            bookHistory.setBooking(bookingMapper.bookingToBookingDto(booking));
            bookHistory.setChangeDate(LocalDate.now());
            bookHistory.setComment(booking.getComment());
            bookHistory.setRoom(roomMapper.roomToRoomDto(booking.getRoom()));
            bookHistory.setCheckInDate(booking.getCheckInDate());
            bookHistory.setCheckOutDate(booking.getCheckOutDate());
            bookHistory.setUser(userMapper.userToUserDto(booking.getGuest()));
            bookHistory.setGuest(userMapper.userToUserDto(booking.getGuest()));
            bookHistory.setStatusBooking(booking.getStatusBooking());

            //ResponseEntity<?> sendAnEmailToTheUsersEmail = sendCode(booking.getGuest().getEmail());

            ResponseEntity<?> saveBookHistory = bookingHistoryService.save(bookHistory);

            log.info("Booking saved: -> " + bookingSaved);
            return bookingDto;
        }catch (BookingException e){
            log.error("Booking not saved: -> " + bookingDto);

            BookingException bookingException = new BookingException("Error while saving booking");
            bookingException.printStackTrace();
            System.out.println(bookingException.getMessage());
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveBooking(SaveBooking saveBooking) {
        try {

            HotelDto hotelDto = hotelService.findById(saveBooking.getHotelId());
            RoomDto roomDto = roomService.findById(saveBooking.getRoomId());
            UserDto userDto = userService.findById(saveBooking.getGuestId());

            BookingDto bookingDto = new BookingDto();
            bookingDto.setHotel(hotelDto);
            bookingDto.setRoom(roomDto);
            bookingDto.setCheckInDate(saveBooking.getCheckInDate());
            bookingDto.setCheckOutDate(saveBooking.getCheckOtDate());
            bookingDto.setGuest(userDto);
            bookingDto.setComment(saveBooking.getComment());
            bookingDto.setPriceOfBook(saveBooking.getPriceOfBook());

            BookingDto savedBooking = save(bookingDto);

            log.info("saveBooking successfully saved: -> " + savedBooking);
            return new ResponseEntity<>(savedBooking, HttpStatus.OK);
        } catch (BookingException b) {
            BookingException bookingException = new BookingException("Error while saving saveBooking booking");
            bookingException.printStackTrace();
            System.out.println(bookingException.getMessage());
            b.printStackTrace();
            System.out.println(b.getMessage());

            log.error("saveBooking method filed, not saved: -> " + saveBooking);

            return new ResponseEntity<>(b.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @Override
    public ResponseEntity<?> update(BookingDto bookingDto) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("User not found"), HttpStatus.NOT_FOUND);
        } else {
            Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
            Booking updatedBooking = bookingDao.save(booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> delete(BookingDto bookingDto) {
        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
        booking.setStatusBooking(EStatusBooking.INACTIVE);
        ResponseEntity<?> bookingDeleted = update(bookingMapper.bookingToBookingDto(booking));
        if (bookingDeleted.getStatusCode().equals(HttpStatus.OK)) {
            return new ResponseEntity<>(bookingDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Message.of("Booking not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public BookingDto findById(Long id) {
        Booking booking = bookingDao.findById(id).orElse(null);
        //if (booking != null) {
        return bookingMapper.bookingToBookingDto(booking);
        //}
        //return null;


    }

    @Override
    public ResponseEntity<?> cancelBooking(CancelBooking cancelBooking) {
        try {
            BookingDto booking = findById(cancelBooking.getBookingId());
            Booking entityBooking = bookingMapper.bookingDtoToBooking(booking);
            entityBooking.setStatusBooking(EStatusBooking.INACTIVE);
            ResponseEntity<?> canceledBooking = update(bookingMapper.bookingToBookingDto(entityBooking));
            UserDto userDto = userService.findById(cancelBooking.getUserId());


            BookingHistoryDto bookHistory = new BookingHistoryDto();
            bookHistory.setBooking(booking);
            bookHistory.setChangeDate(LocalDate.now());
            bookHistory.setComment(cancelBooking.getComment());
            bookHistory.setRoom(roomMapper.roomToRoomDto(entityBooking.getRoom()));
            bookHistory.setCheckInDate(entityBooking.getCheckInDate());
            bookHistory.setCheckOutDate(entityBooking.getCheckOutDate());
            bookHistory.setUser(userDto);
            bookHistory.setGuest(userMapper.userToUserDto(entityBooking.getGuest()));
            bookHistory.setStatusBooking(entityBooking.getStatusBooking());


            ResponseEntity<?> savedBookingHistory = bookingHistoryService.save(bookHistory);
            // ResponseEntity<?> sendAnEmailToTheUsersEmail = sendCode2(entityBooking.getGuest().getEmail());
            // if (canceledBooking.getStatusCode().equals(HttpStatus.OK) && savedBookingHistory.getStatusCode().equals(HttpStatus.OK) && savedBookingHistory.getStatusCode().equals(HttpStatus.OK)) {
            log.info("Booking successfully canceled: -> " + savedBookingHistory);
            return new ResponseEntity<>(canceledBooking, HttpStatus.OK);
            //}
        } catch (CancelBookingErrorException c) {

            log.error("cancel Booking filed: -> " + cancelBooking.getBookingId() + " userId " + cancelBooking.getUserId());
            CancelBookingErrorException ex = new CancelBookingErrorException("Error while cancelling booking");
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<?> sendCode(String email) {
//        try {
//            emailSender.sendSimpleMessage(email, "You have successfully booked a hotel", ".");
//            log.info("Message successfully sent to: -> " + email);
//            return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error("Message filed while sending message to: ->" + email);
//            return new ResponseEntity<>(Message.of("Error while sending code to email"), HttpStatus.NOT_IMPLEMENTED);
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> sendCode2(String email) {
//        try {
//            emailSender.sendSimpleMessage(email, "You have successfully canceled your hotel reservation", ".");
//            log.info("Message successfully sent to: -> " + email);
//
//            return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error("Message filed while sending message to: ->" + email);
//
//            return new ResponseEntity<>(Message.of("Error while sending code to email"), HttpStatus.NOT_IMPLEMENTED);
//        }
//    }

    @Override
    public List<BookingDto> findAllByHotel(Long hotelId) {
        List<Booking> booking = bookingDao.findAllByHotel(hotelId);
        System.out.println(booking.get(0).getCheckOutDate());
        System.out.println(booking.get(0).getStatusBooking());
        if (booking == null) log.error("Bookings not found from database with this hotelId" + hotelId);
        log.info("Bookings successfully found from database: -> " + booking);
        return bookingMapper.bookingListToBookingDtoList(booking);
    }

    @Override
    public List<BookingDto> findAllBooking(Long hotelId, int numberPerson, LocalDate checkInDate, LocalDate checkOutDate) {
            List<Booking> booking = bookingDao.findAllBooking(hotelId, numberPerson, checkInDate, checkOutDate);
            if (booking == null)
                log.error("Booking not found from database with this information: -> " + numberPerson + " " + checkInDate + " " + " " + checkOutDate);
            log.info("Booking successfully found from database : -> " + booking);
            return bookingMapper.bookingListToBookingDtoList(booking);
        }

        @Override
        public List<BookingDto> findAllByRoomAndActive (RoomDto roomDto){
            List<Booking> bookings = bookingDao.findAllByRoomAndStatusBooking(roomMapper.roomDtoToRoom(roomDto),EStatusBooking.ACTIVE);
            return bookingMapper.bookingListToBookingDtoList(bookings);

        }
    }