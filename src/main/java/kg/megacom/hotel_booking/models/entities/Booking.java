package kg.megacom.hotel_booking.models.entities;

import kg.megacom.hotel_booking.models.enums.EStatusBooking;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_booking")
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    Room room;

    LocalDate checkInDate;
    LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn (name = "guest_id")
    User guest;
    String comment;
    float priceOfBook;
    @Enumerated(EnumType.STRING)
    EStatusBooking statusBooking;

}
