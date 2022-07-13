package kg.megacom.hotel_booking.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.megacom.hotel_booking.models.enums.EHotelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    Long id;
    String name;
    String description;
    String address;


    @JsonIgnore byte star;

    String phone;
    double currentScore;
    String email;
    CityDto city;
    EHotelStatus hotelStatus;
    UserDto manager;
}
