package kg.megacom.hotel_booking.models.dtos;

import kg.megacom.hotel_booking.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {
    Long id;
    String name;
    String email;
    ERole role;
    boolean active;
}
