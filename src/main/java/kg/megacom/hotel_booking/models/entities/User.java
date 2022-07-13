package kg.megacom.hotel_booking.models.entities;

import kg.megacom.hotel_booking.models.enums.ERole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "tb_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    @Size(max = 25)
    String name;
    @NotBlank
    @Size(max = 50)
    @Email
    String email;
    @Enumerated(EnumType.STRING)
    ERole role;
    boolean active;

}
