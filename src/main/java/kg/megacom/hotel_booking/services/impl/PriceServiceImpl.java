package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.PriceDao;
import kg.megacom.hotel_booking.mappers.PriceMapper;
import kg.megacom.hotel_booking.mappers.RoomMapper;
import kg.megacom.hotel_booking.models.dtos.PriceDto;
import kg.megacom.hotel_booking.models.dtos.RoomCategoryDto;
import kg.megacom.hotel_booking.models.dtos.RoomDto;
import kg.megacom.hotel_booking.models.entities.Price;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceDao priceDao;
    private PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Override
    public PriceDto save(PriceDto priceDto) {
        Price price = priceMapper.priceDtoToPrice(priceDto);
        price.setActive(true);
        Price savePrice = priceDao.save(price);
        return priceMapper.priceToPriceDto(savePrice);
    }

    @Override
    public ResponseEntity<?> update(PriceDto priceDto) {
        boolean isExists = priceDao.existsById(priceDto.getId());
        if(!isExists){
            return new ResponseEntity<>(Message.of("Price not found"), HttpStatus.NOT_FOUND);
        }else{
            Price price = priceMapper.priceDtoToPrice(priceDto);
            Price updatePrice = priceDao.save(price);
            return new ResponseEntity<>(updatePrice,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(PriceDto priceDto) {
        Price price = priceMapper.priceDtoToPrice(priceDto);
        price.setActive(false);
        ResponseEntity<?>deletedPrice = update(priceMapper.priceToPriceDto(price));
        if(deletedPrice.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedPrice,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("Price not deleted"),HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public PriceDto findPrice(RoomCategoryDto roomCategoryDto, LocalDate date) {
        Price price  = priceDao.findByRoomCategoryAndStartDateAndEndDate(roomCategoryDto.getId(),date);
        return priceMapper.priceToPriceDto(price);
    }
}
