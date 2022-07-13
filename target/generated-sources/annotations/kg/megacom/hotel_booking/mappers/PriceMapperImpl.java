package kg.megacom.hotel_booking.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.hotel_booking.models.dtos.PriceDto;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.entities.Price;
import kg.megacom.hotel_booking.models.entities.RoomCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T19:26:13+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class PriceMapperImpl implements PriceMapper {

    @Override
    public Price priceDtoToPrice(PriceDto priceDto) {
        if ( priceDto == null ) {
            return null;
        }

        Price price = new Price();

        price.setId( priceDto.getId() );
        price.setPrice( priceDto.getPrice() );
        price.setStartDate( priceDto.getStartDate() );
        price.setEndDate( priceDto.getEndDate() );
        price.setActive( priceDto.isActive() );
        price.setRoomCategory( roomCategoryDtoToRoomCategory( priceDto.getRoomCategory() ) );

        return price;
    }

    @Override
    public PriceDto priceToPriceDto(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDto priceDto = new PriceDto();

        priceDto.setId( price.getId() );
        priceDto.setPrice( price.getPrice() );
        priceDto.setStartDate( price.getStartDate() );
        priceDto.setEndDate( price.getEndDate() );
        priceDto.setActive( price.isActive() );
        priceDto.setRoomCategory( roomCategoryToRoomCategoryDto( price.getRoomCategory() ) );

        return priceDto;
    }

    @Override
    public List<Price> priceDtoListToPriceList(List<PriceDto> priceDtos) {
        if ( priceDtos == null ) {
            return null;
        }

        List<Price> list = new ArrayList<Price>( priceDtos.size() );
        for ( PriceDto priceDto : priceDtos ) {
            list.add( priceDtoToPrice( priceDto ) );
        }

        return list;
    }

    @Override
    public List<PriceDto> priceListToPriceDtoList(List<Price> prices) {
        if ( prices == null ) {
            return null;
        }

        List<PriceDto> list = new ArrayList<PriceDto>( prices.size() );
        for ( Price price : prices ) {
            list.add( priceToPriceDto( price ) );
        }

        return list;
    }

    protected RoomCategory roomCategoryDtoToRoomCategory(RoomCategoryDto roomCategoryDto) {
        if ( roomCategoryDto == null ) {
            return null;
        }

        RoomCategory roomCategory = new RoomCategory();

        roomCategory.setId( roomCategoryDto.getId() );
        roomCategory.setTypeOfRoom( roomCategoryDto.getTypeOfRoom() );

        return roomCategory;
    }

    protected RoomCategoryDto roomCategoryToRoomCategoryDto(RoomCategory roomCategory) {
        if ( roomCategory == null ) {
            return null;
        }

        RoomCategoryDto roomCategoryDto = new RoomCategoryDto();

        roomCategoryDto.setId( roomCategory.getId() );
        roomCategoryDto.setTypeOfRoom( roomCategory.getTypeOfRoom() );

        return roomCategoryDto;
    }
}
