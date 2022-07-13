package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.PriceDto;
import kg.megacom.hotel_booking.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price priceDtoToPrice(PriceDto priceDto);

    PriceDto priceToPriceDto(Price price);

    List<Price> priceDtoListToPriceList(List<PriceDto> priceDtos);

    List<PriceDto> priceListToPriceDtoList(List<Price> prices);
}
