package kg.megacom.hotel_booking.dao;

import kg.megacom.hotel_booking.models.entities.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryDao extends JpaRepository<RoomCategory,Long> {


}
