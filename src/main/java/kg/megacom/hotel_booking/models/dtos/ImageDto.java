package kg.megacom.hotel_booking.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDto {
    @JsonIgnore
    Long id;
    String link;

    int position;
    HotelDto hotel;
}
