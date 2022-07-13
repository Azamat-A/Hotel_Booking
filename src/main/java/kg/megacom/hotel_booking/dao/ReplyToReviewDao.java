package kg.megacom.hotel_booking.dao;

import kg.megacom.hotel_booking.models.entities.ReplyToReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyToReviewDao extends JpaRepository<ReplyToReview, Long> {

}
