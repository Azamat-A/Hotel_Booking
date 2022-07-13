package kg.megacom.hotel_booking.dao;

import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review,Long> {
     List<Review> findAllByActiveTrueAndHotel(Hotel hotel);
}
