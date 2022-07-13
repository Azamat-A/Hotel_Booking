package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.CityDao;
import kg.megacom.hotel_booking.mappers.CityMapper;
import kg.megacom.hotel_booking.models.dtos.CityDto;
import kg.megacom.hotel_booking.models.entities.City;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private  CityDao cityDao;
    private final CityMapper cityMapper = CityMapper.INSTANCE;


    @Override
    public ResponseEntity<?> save(CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        city.setActive(true);
        City saveCity = cityDao.save(city);
        return new ResponseEntity<>(saveCity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(CityDto cityDto) {
        boolean isExists = cityDao.existsById(cityDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("City not found"), HttpStatus.NOT_FOUND);
        }else{
            City city = cityMapper.cityDtoToCity(cityDto);
            City updatedCity = cityDao.save(city);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        city.setActive(false);
        ResponseEntity<?> cityDeleted = update(cityMapper.cityToCityDto(city));
        if(cityDeleted.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(cityDeleted, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("City not deleted"), HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public CityDto findById(Long id) {
        City city = cityDao.findById(id).orElse(null);
        if(city != null){
            return cityMapper.cityToCityDto(city);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<City> cities = cityDao.findAllByName();
        return ResponseEntity.ok(cityMapper.cityListToCityDtoList(cities));
    }
}
