package kg.megacom.hotel_booking.models.entities;

import kg.megacom.hotel_booking.models.enums.EStatusBooking;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_booking_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingHistory {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "booking_id")
     Booking booking;
     LocalDate changeDate;
     String comment;
     @ManyToOne
     @JoinColumn(name = "room_id")
     Room room;
     LocalDate checkInDate;
     LocalDate checkOutDate;

     @ManyToOne
     @JoinColumn(name = "user_id")
     User guest;
     @ManyToOne
     @JoinColumn(name = "changer_user_id")
     User user;
     boolean active;
     @Enumerated(EnumType.STRING)
     EStatusBooking statusBooking;
}
