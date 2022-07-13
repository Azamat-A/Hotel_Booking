package kg.megacom.hotel_booking.dao;

import kg.megacom.hotel_booking.models.entities.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryDao extends JpaRepository<BookingHistory, Long> {

}
