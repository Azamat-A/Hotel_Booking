package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.ReplyToReviewDao;
import kg.megacom.hotel_booking.mappers.ReplyToReviewMapper;
import kg.megacom.hotel_booking.models.dtos.ReplyToReviewDto;
import kg.megacom.hotel_booking.models.entities.ReplyToReview;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.ReplyToReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReplyToReviewServiceImpl implements ReplyToReviewService {

    @Autowired
    private ReplyToReviewDao replyToReviewDao;
    private final ReplyToReviewMapper replyToReviewMapper = ReplyToReviewMapper.INSTANCE;


    @Override
    public ResponseEntity<?> save(ReplyToReviewDto replyToReviewDto) {
        ReplyToReview replyToReview = replyToReviewMapper.replyToReviewDtoToReplyToReview(replyToReviewDto);
        replyToReview.setDate(LocalDate.now());
        ReplyToReview saveReplyToReview = replyToReviewDao.save(replyToReview);
        return new ResponseEntity<>(saveReplyToReview, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(ReplyToReviewDto replyToReviewDto) {
        ReplyToReview replyToReview = replyToReviewMapper.replyToReviewDtoToReplyToReview(replyToReviewDto);
        replyToReviewDao.deleteById(replyToReview.getId());
        return new ResponseEntity<>(Message.of("ReplyToReview deleted"), HttpStatus.OK);
    }
}
