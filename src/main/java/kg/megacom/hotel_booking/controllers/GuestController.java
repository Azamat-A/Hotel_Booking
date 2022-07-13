package kg.megacom.hotel_booking.controllers;

import kg.megacom.hotel_booking.models.dtos.BookingDto;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.ReviewDto;
import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.request.SaveBooking;
import kg.megacom.hotel_booking.models.response.HotelFilterResponse;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.BookingService;
import kg.megacom.hotel_booking.services.HotelService;
import kg.megacom.hotel_booking.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/guest")
public class GuestController {

    @Autowired
    BookingService bookingService;
    @Autowired
    private ReviewService reviewService;


    @Autowired
    HotelService hotelService;


    @GetMapping("/mainPage")
    public List<HotelDto> getMainPage(@RequestParam Long  cityId){
        return  hotelService.findAllHotelsByCity(cityId);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam Long cityId ,
                                    @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                                    @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                                    @RequestParam int guestAmount,
                                    @RequestParam EBedType bedType){
        return hotelService.filter(cityId,checkInDate,checkOutDate,guestAmount, bedType);
    }

    @PostMapping("saveBooking")
    //@PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> saveBooking(@RequestBody SaveBooking saveBooking){
        return bookingService.saveBooking(saveBooking);}

    @PutMapping("/updateBooking")
    public ResponseEntity<?> updateBooking(@RequestBody BookingDto bookingDto){
        return bookingService.update(bookingDto);
    }

    @DeleteMapping("/deleteBooking")
    public ResponseEntity<?> deleteBooking(@RequestBody BookingDto bookingDto){
        return bookingService.delete(bookingDto);
    }
    @PostMapping("/saveReview")
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto){
        return reviewService.save(reviewDto);
    }

    @PutMapping("/updateReview")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto){
        return reviewService.update(reviewDto);
    }
    @PutMapping("/deleteReview")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto){
        return reviewService.delete(reviewDto);
    }
    @GetMapping("/get/reviewsByHotel")
    public  ResponseEntity<?> findAllByHotel(@RequestParam Long hotelId){
        List<ReviewDto> reviewDtos = reviewService.findAllByHotelAndActive(hotelId);
        if(reviewDtos.isEmpty()){
            return  new ResponseEntity<>(Message.of("Отзывы по отелю отсутствуют"), HttpStatus.NO_CONTENT);
        }else{
           return  new ResponseEntity<>(reviewDtos,HttpStatus.OK);
        }
    }



}
