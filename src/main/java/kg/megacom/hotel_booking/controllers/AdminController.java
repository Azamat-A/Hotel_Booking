package kg.megacom.hotel_booking.controllers;

import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.ReviewDto;
import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    @Autowired
    CityService cityService;
    @Autowired
    HotelService hotelService;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;



    @PostMapping("/saveCity")
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto){
        return cityService.save(cityDto);
    }

    @PutMapping("/updateCity")
    public ResponseEntity<?> updateCity(@RequestBody CityDto cityDto){
        return cityService.update(cityDto);
    }

    @PutMapping("/deleteCity")
    public ResponseEntity<?> deleteCity(@RequestBody CityDto cityDto){
        return cityService.delete(cityDto);
    }

    @GetMapping("/get/list")
    public ResponseEntity<?> findAll(){
        return  cityService.findAll();
    }
    @PostMapping("/saveHotel")
    public ResponseEntity<?> saveHotel(@RequestBody HotelDto hotelDto){
        return hotelService.save(hotelDto);
    }
    @DeleteMapping("/deleteHotel")
    public ResponseEntity<?> deleteHotel(@RequestParam Long hotelId){
        return hotelService.delete(hotelId);
    }

    @PutMapping("/updateHotel")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        return  hotelService.update(hotelDto);
    }

    @PostMapping("/saveUser")
   public  UserDto saveUser(@RequestBody UserDto userDto){
        return  userService.save(userDto);
   }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        return userService.update(userDto);
    }
    @PutMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto){
        return userService.delete(userDto);
    }





//    @PutMapping("/blockHotel")
//    public ResponseEntity<?> blockHotel(@RequestParam Long hotelId){
//        return hotelService.hotelBLock(hotelId);
//    }
//
//    @PutMapping("/confirmHotel")
//    public ResponseEntity<?> confirmHotel(@RequestParam Long hotelId){
//        return hotelService.confirm(hotelId);
//    }
}
