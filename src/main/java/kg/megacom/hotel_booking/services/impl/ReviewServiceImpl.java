package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.ReviewDao;
import kg.megacom.hotel_booking.mappers.HotelMapper;
import kg.megacom.hotel_booking.mappers.ReviewMapper;
import kg.megacom.hotel_booking.models.dtos.HotelDto;
import kg.megacom.hotel_booking.models.dtos.ReviewDto;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.entities.Review;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.HotelService;
import kg.megacom.hotel_booking.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    private ReviewMapper reviewMapper;
    private HotelMapper hotelMapper;

    @Autowired
    private HotelService hotelService;


    @Override
    public ResponseEntity<?> save(ReviewDto reviewDto) {
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        review.setActive(true);
        Review saveReview = reviewDao.save(review);
        return new ResponseEntity<>(saveReview, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ReviewDto reviewDto) {
        boolean isExists = reviewDao.existsById(reviewDto.getId());
        if(!isExists){
            return new ResponseEntity<>(Message.of("Review not found"),HttpStatus.NOT_FOUND);
        }else{
            Review review = reviewMapper.reviewDtoToReview(reviewDto);
            Review updateReview = reviewDao.save(review);
            return new ResponseEntity<>(updateReview,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewDto reviewDto) {

        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        review.setActive(true);
        ResponseEntity<?> deletedReview = update(reviewMapper.reviewToReviewDto(review));
        if (deletedReview.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedReview,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("Review not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<ReviewDto> findAllByHotelAndActive(Long hotelId) {
        HotelDto hotel = hotelService.findById(hotelId);
        return reviewMapper.reviewListToReviewDtoList(reviewDao.findAllByActiveTrueAndHotel(hotelMapper.hotelDtoToHotel(hotel)));
    }


}
