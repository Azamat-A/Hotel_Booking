package kg.megacom.hotel_booking.dao;


import feign.Param;
import kg.megacom.hotel_booking.models.entities.City;
import kg.megacom.hotel_booking.models.entities.Hotel;
import kg.megacom.hotel_booking.models.enums.EBedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelDao extends JpaRepository<Hotel,Long> {

    @Query(value = "select * from hotels h  where h.city_id = ?1 ORDER BY h.current_score DESC",nativeQuery = true)
    List<Hotel> findAllByCity(Long cityId);

//    @Query("select h from Hotel h where h.city = ?1 order by h.currentScore DESC")
//    List<Hotel> findAllByCityAndCurrentScore(City city);


    @Query(value = "select * from tb_hotel h where h.city_id = :cityId and exists (select * from tb_room r where r.hotel_id = h.id and r.bed_type = :#{#bedType.name()})", nativeQuery = true)
    List<Hotel> findAllByCityAndBedType(@Param("cityId") Long cityId, @Param("bedType") EBedType bedType);
    @Query(value = "select * from tb_hotel h inner join tb_room r on r.hotel_id = h.id where h.city_id = ?1 and r.capacity >= ?2 and r.bed_type = :#{#bedtype.name()}",nativeQuery = true)
    List<Hotel> findAll(Long cityId, int capacityPerson,@Param("bedType")EBedType bedType);


}
