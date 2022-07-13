package kg.megacom.hotel_booking.models.entities;

import kg.megacom.hotel_booking.models.enums.EHotelStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_hotel")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    String address;
    byte star;
    String phone;
    double currentScore;
    String email;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Enumerated(EnumType.STRING)
    EHotelStatus hotelStatus;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    User manager;
}
