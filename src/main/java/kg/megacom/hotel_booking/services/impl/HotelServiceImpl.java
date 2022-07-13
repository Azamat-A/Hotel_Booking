package kg.megacom.hotel_booking.services.impl;
import kg.megacom.hotel_booking.dao.BookingDao;
import kg.megacom.hotel_booking.dao.HotelDao;
import kg.megacom.hotel_booking.mappers.CityMapper;
import kg.megacom.hotel_booking.mappers.HotelMapper;
import kg.megacom.hotel_booking.mappers.RoomMapper;
import kg.megacom.hotel_booking.models.dtos.*;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.enums.EHotelStatus;
import kg.megacom.hotel_booking.models.enums.EStatusBooking;
import kg.megacom.hotel_booking.models.response.HotelFilterResponse;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.models.response.RoomFilterResponse;
import kg.megacom.hotel_booking.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;;import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CityService cityService;
    @Autowired
    private  HotelService hotelService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private PriceService priceService;

    @Autowired
    private  RoomCategoryService roomCategoryService;

    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;
    private final CityMapper cityMapper = CityMapper.INSTANCE;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public ResponseEntity<?> save(HotelDto hotelDto) {
        UserDto managerSaved = userService.save(hotelDto.getManager());

        hotelDto.setManager(managerSaved);
        Hotel savedHotel = hotelDao.save(hotelMapper.hotelDtoToHotel(hotelDto));
        return new ResponseEntity<>(hotelMapper.hotelToHotelDto(savedHotel), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(HotelDto hotelDto) {
       boolean isExists = hotelDao.existsById(hotelDto.getId());
       if(!isExists){
           log.error("Hotel not found from database: " + hotelDto);
           return new ResponseEntity<>(Message.of("Hotel not found"),HttpStatus.NOT_FOUND);
       }else{
           Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto);
           Hotel updatedHotel = hotelDao.save(hotel);
           return  new ResponseEntity<>(updatedHotel,HttpStatus.OK);
       }

    }

    @Override
    public ResponseEntity<?> delete(Long hotelId) {
       HotelDto hotelDto = findById(hotelId);
       if (hotelDto == null){
           return new ResponseEntity<>(Message.of("Hotel not found"),HttpStatus.NOT_FOUND);
       }

       Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto);
       hotel.setHotelStatus(EHotelStatus.INACTIVE);
       ResponseEntity<?> deletedHotel = update(hotelMapper.hotelToHotelDto(hotel));
       if(deletedHotel.getStatusCode().equals(HttpStatus.OK)){
           return new ResponseEntity<>(deletedHotel,HttpStatus.OK);
       }else {
           return  new ResponseEntity<>(Message.of("Hotel not deleted"),HttpStatus.NOT_FOUND);
       }
    }

    @Override
    public HotelDto findById(Long hotelId) {
       Hotel hotel = hotelDao.findById(hotelId).orElse(null);
       if (hotel == null) log.error("Hotel not found from database: " + hotelId);
       log.info("Hotel successfully found: " + hotel);
       return  hotelMapper.hotelToHotelDto(hotel);
    }

    @Override
    public List<HotelDto> findAll() {
        List<Hotel> hotels = hotelDao.findAll();
        return hotelMapper.hotelListToHotelDtoList(hotels);
    }

    @Override
    public ResponseEntity<?> hotelBLock(Long hotelId)  {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null) {
            log.error("Hotel not found from database:  " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto);
        hotel.setHotelStatus(EHotelStatus.BLOCK);
        ResponseEntity<?> blockHotel = update(hotelMapper.hotelToHotelDto(hotel));
        if (blockHotel.getStatusCode().equals(HttpStatus.OK)) {
            log.info("Hotel successfully blocked: " + blockHotel);
            return new ResponseEntity<>(blockHotel, HttpStatus.OK);
        } else {
            log.error("Failed while blocking hotel:  " + hotel);
            return new ResponseEntity<>(Message.of("Hotel not blocked"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> confirm(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null) {
            log.error("Hotel not found from database: " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto);
        hotel.setHotelStatus(EHotelStatus.ACTIVE);
        ResponseEntity<?> blockHotel = update(hotelMapper.hotelToHotelDto(hotel));
        if (blockHotel.getStatusCode().equals(HttpStatus.OK)){
            log.error("Logger successfully confirmed: " + blockHotel);
            return  new ResponseEntity<>(blockHotel,HttpStatus.OK);
    }else{
            log.error("Hotel not confirmed: -> " + hotel);
            return new ResponseEntity<>(Message.of("Hotel not confirmed"),HttpStatus.NOT_FOUND);
        }

}

    @Override
    public ResponseEntity<?> Rating(List<HotelFilterResponse> hotels) {

        List<HotelFilterResponse> sortedList = hotels.stream().sorted(Comparator.comparing(HotelFilterResponse::getCurrentScore)).collect(Collectors.toList());

        return new ResponseEntity<>(sortedList,HttpStatus.OK);
    }

    @Override
    public List<HotelDto> findAllHotelsByCity(Long cityId) {
        CityDto cityDto = cityService.findById(cityId);
        if (cityDto != null){
            List<Hotel> hotels = hotelDao.findAllByCity(cityDto.getId());
            if (!hotels.isEmpty()){
                return  hotelMapper.hotelListToHotelDtoList(hotels);
            }else{
                return  null;
            }
        }else{
            return  null;
        }

    }

    @Override
    public ResponseEntity<?> filter(Long cityId, LocalDate checkInDate, LocalDate checkOutDate, int questAmount, EBedType bedType) {

        System.out.println(cityId);
        System.out.println(bedType);
        // получили все отели по городу и наличию нужного типа комнаты
        List<Hotel> hotels = hotelDao.findAllByCityAndBedType(cityId, bedType);
        List<HotelFilterResponse> filteredHotels = new ArrayList<>();

        System.out.println(hotels.size());
        List<Hotel> availabeHotels = new ArrayList<>();
        hotels.stream().forEach(hotel -> {
            // получили все номера отеля по типу комнаты
            List<RoomDto> rooms = roomService.findAllRoomsByHotel(hotel, bedType, questAmount );
            List<RoomDto> availableRooms = new ArrayList<>();
            System.out.println(rooms.size());
            rooms.stream().forEach(room -> {
                List<BookingDto> bookings = bookingService.findAllByRoomAndActive(room);
                if (bookings.isEmpty()) {

                    availableRooms.add(room);
                } else {
                    AtomicBoolean isBooked = new AtomicBoolean(false);
                    bookings.stream().forEach(z-> {
                        if (checkIsBooked(z, checkInDate, checkOutDate)) {
                            System.out.println("Room is booked");
                            isBooked.set(true);
                        }
                    });

                    if(isBooked.equals(false)){
                        availableRooms.add(room);
                    }
                }
            });
            if(!availableRooms.isEmpty()){
                availabeHotels.add(hotel);
                HotelFilterResponse responce = formHotelResponse(hotel,availableRooms,checkInDate, checkOutDate);
                System.out.println(responce);
                filteredHotels.add(responce);

            }
        });
        return new ResponseEntity<>(filteredHotels, HttpStatus.OK);
    }

    private  boolean checkIsBooked(BookingDto bookingDto, LocalDate startDate,LocalDate endDate){
        if(startDate.equals(bookingDto.getCheckInDate())
            || startDate.equals(bookingDto.getCheckOutDate())
            || (startDate.isAfter(bookingDto.getCheckInDate()) && startDate.isBefore(bookingDto.getCheckOutDate()))
            || endDate.equals(bookingDto.getCheckInDate())
            || endDate.equals(bookingDto.getCheckOutDate())
            || (endDate.isAfter(bookingDto.getCheckInDate()) && endDate.isBefore(bookingDto.getCheckOutDate()))
            || (startDate.isBefore(bookingDto.getCheckInDate()) && endDate.isAfter(bookingDto.getCheckOutDate()))
        ){
            return  true;
        }else{
            return false;
        }

     }


    @Override
    public void countCurrentScore() {

        List<HotelDto> hotelDtos = findAll();
        hotelDtos.stream().forEach(x->{
            List<ReviewDto> reviewDtos = reviewService.findAllByHotelAndActive(x.getId());
            Double sum = reviewDtos.stream().mapToDouble(ReviewDto::getScore).sum();
            Double currentScore = Math.round((sum / reviewDtos.size())/10.0) * 10.0;
            x.setCurrentScore(currentScore);
            update(x);
        });

    }

    private HotelFilterResponse formHotelResponse(Hotel hotel, List<RoomDto> rooms,LocalDate checkIn,LocalDate checkOut){
        HotelFilterResponse hotelResponse = new HotelFilterResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setDescription(hotel.getDescription());
        hotelResponse.setAddress(hotel.getAddress());
        hotelResponse.setEmail(hotel.getEmail());
        hotelResponse.setCurrentScore(hotel.getCurrentScore());
        hotelResponse.setPhone(hotel.getPhone());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setCurrentScore(hotel.getCurrentScore());

        List<RoomFilterResponse> roomResponse = new ArrayList<>();
        rooms.stream().forEach(room -> {
            PriceDto priceCheckIn = priceService.findPrice(room.getRoomCategory(),checkIn);
            PriceDto priceCheckOut = priceService.findPrice(room.getRoomCategory(),checkOut);

            if(priceCheckIn != null && priceCheckOut !=null){
                if (priceCheckIn.getPrice() == priceCheckOut.getPrice()){
//                    Duration off = Duration.between(checkIn.atStartOfDay(),checkOut.atStartOfDay());
//
//                    long diffDays = diff.toDays();
//                    diffDays +=1;
//                    float countPrice = diffDays * priceCheckIn.getPrice();
                    long daysBetween = DAYS.between(checkIn,checkOut) + 1;
                    float totalSum = priceCheckIn.getPrice() * daysBetween;

                    RoomFilterResponse roomFilterResponse = RoomFilterResponse.builder()
                            .bedType(room.getBedType())
                            .capacity(room.getCapacity())
                            .checkInDate(checkIn)
                            .checkOutDate(checkOut)
                            .id(room.getId())
                            .square(room.getSquare())
                            .typeOfView(room.getTypeOfView())
                            .wifi(room.isWifi())
                            .totalSum(totalSum)
                            .build();
                    roomResponse.add(roomFilterResponse);
                }else {
                    Duration diff = Duration.between(checkIn.atStartOfDay(),priceCheckIn.getEndDate());

                    long diffDays = diff.toDays();
                    diffDays += 1;

                    float sumBeginning = diffDays * priceCheckIn.getPrice();

                    Duration diff2 = Duration.between(priceCheckOut.getStartDate(),checkOut);

                    long diffDays2 = diff.toDays();
                    diffDays2 += 1;

                    float sumEnding = diffDays2 * priceCheckOut.getPrice();

                    float totalSum = sumBeginning + sumEnding;

                    RoomFilterResponse roomFilterResponse = RoomFilterResponse.builder()
                            .bedType(room.getBedType())
                            .capacity(room.getCapacity())
                            .checkInDate(checkIn)
                            .checkOutDate(checkOut)
                            .id(room.getId())
                            .square(room.getSquare())
                            .typeOfView(room.getTypeOfView())
                            .wifi(room.isWifi())
                            .totalSum(totalSum)
                            .build();
                    roomResponse.add(roomFilterResponse);
                }
            }

        });

        hotelResponse.setAvailableRooms(roomResponse);

        return hotelResponse;
    }


}
