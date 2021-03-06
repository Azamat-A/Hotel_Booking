package kg.megacom.hotel_booking.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.megacom.hotel_booking.models.entities.RoomCategory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {
@JsonIgnore
   Long id;
   float price;

   @DateTimeFormat(pattern = "yyyy-MM-dd")
   LocalDate startDate;

   @DateTimeFormat(pattern = "yyyy-MM-dd")
   LocalDate endDate;

   boolean active;
   RoomCategoryDto roomCategory;
}
