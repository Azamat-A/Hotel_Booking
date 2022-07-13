package kg.megacom.hotel_booking.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.megacom.hotel_booking.models.enums.ETypeOfRoom;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomCategoryDto {
    @JsonIgnore
    Long id;
    ETypeOfRoom typeOfRoom;

}
