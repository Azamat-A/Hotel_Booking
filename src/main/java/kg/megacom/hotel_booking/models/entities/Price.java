package kg.megacom.hotel_booking.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table (name = "tb_price")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {

    @Id
    @GeneratedValue
    Long id;
    float price;
    LocalDate startDate;
    LocalDate endDate;

    boolean active;
    @ManyToOne
    @JoinColumn(name = "room_category_id")
    RoomCategory roomCategory;


}
