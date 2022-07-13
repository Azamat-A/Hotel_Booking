package kg.megacom.hotel_booking.models.entities;

import kg.megacom.hotel_booking.models.enums.EBedType;
import kg.megacom.hotel_booking.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_room")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int capacity;
    @Enumerated(EnumType.STRING)
    EBedType bedType;
    float square;
    boolean wifi;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Enumerated(EnumType.STRING)
    ETypeOfView typeOfView;

    boolean active;

    @ManyToOne
    @JoinColumn(name = "room_category_id")
    RoomCategory roomCategory;
}
