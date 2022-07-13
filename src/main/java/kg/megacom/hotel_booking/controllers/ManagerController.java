package kg.megacom.hotel_booking.controllers;

import kg.megacom.hotel_booking.models.dtos.*;
import kg.megacom.hotel_booking.models.request.SaveCategoryAndPrice;
import kg.megacom.hotel_booking.models.request.SaveRoom;
import kg.megacom.hotel_booking.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/manager")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ManagerController {
@Autowired HotelService hotelService;
@Autowired BookingService bookingService;
@Autowired ReplyToReviewService replyToReviewService;
@Autowired RoomService roomService;
@Autowired FileService fileService;
@Autowired PriceService priceService;
@Autowired RoomCategoryService roomCategoryService;
@Autowired ImageService imageService;

@Autowired ReviewService reviewService;


    @PutMapping("/updateHotel")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        return  hotelService.update(hotelDto);
    }

    @PostMapping("/uploadPhotoToHotel")
    public ResponseEntity<?> uploadImageToHotel(@RequestParam MultipartFile file, @RequestParam Long hotelId, @RequestParam int position){
        return fileService.uploadImageToHotel(file,hotelId,position);
    }

    @PutMapping("/updateRoom")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        return roomService.update(roomDto);
    }

    @PutMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestBody RoomDto roomDto){
        return roomService.delete(roomDto);
    }

    @PutMapping("/updatePrice")
    public ResponseEntity<?> updatePrice(@RequestBody PriceDto priceDto){
        return priceService.update(priceDto);
    }

    @PutMapping("/deletePrice")
    public ResponseEntity<?> deletePrice(@RequestBody PriceDto priceDto){
        return priceService.delete(priceDto);
    }

    @PutMapping("/deleteReview")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto){
        return reviewService.delete(reviewDto);
    }
    @PostMapping("/saveReplyToReview")
    public ResponseEntity<?> saveReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.save(replyToReviewDto);
    }
    @DeleteMapping("/deleteReplyToReview")
    public  ResponseEntity<?> deleteReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.delete(replyToReviewDto);
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<?> saveCategory(@RequestBody SaveCategoryAndPrice saveCategoryAndPrice){
        return roomCategoryService.saveCategoryAndPrice(saveCategoryAndPrice);
    }



//    @PostMapping("/saveImage")
//    public ResponseEntity<?> saveImage(@RequestBody ImageDto imageDto){
//        return imageService.saveImage(imageDto);
//    }
//    @PutMapping("/updateImage")
//    public ResponseEntity<?> updateImage(@RequestBody ImageDto imageDto){
//        return imageService.updateImage(imageDto);
//    }
//    @DeleteMapping("/deleteImage")
//    public ResponseEntity<?> deleteImage(@RequestParam Long imageId){
//        return imageService.deleteImage(imageId);
//    }
//
    @PostMapping("/saveRoom")
    public ResponseEntity<?> saveRoom(@RequestBody SaveRoom saveRoom){
        return roomService.saveRoom(saveRoom);
    }




}
