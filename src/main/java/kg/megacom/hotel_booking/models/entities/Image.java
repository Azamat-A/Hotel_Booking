package kg.megacom.hotel_booking.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Table(name = "tb_image")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE )
    Long id;
    String link;

    int position;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
}
