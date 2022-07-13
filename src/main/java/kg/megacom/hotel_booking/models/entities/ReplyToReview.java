package kg.megacom.hotel_booking.models.entities;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_review_response")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyToReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    LocalDate date;

    @OneToOne
    @JoinColumn(name = "review_id")
    Review review;
}
