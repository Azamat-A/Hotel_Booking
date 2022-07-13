package kg.megacom.hotel_booking.dao;

import kg.megacom.hotel_booking.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao extends JpaRepository<City,Long> {

    @Query(value ="select * from tb_city c order by c.name ASC",nativeQuery = true)
    List<City>findAllByName();

}
