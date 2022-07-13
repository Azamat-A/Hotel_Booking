package kg.megacom.hotel_booking.services.impl;

import kg.megacom.hotel_booking.dao.UserDao;
import kg.megacom.hotel_booking.mappers.UserMapper;
import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.models.entities.User;
import kg.megacom.hotel_booking.models.response.Message;
import kg.megacom.hotel_booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;
    private  final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setActive(true);
        User saveUser = userDao.save(user);
        return userMapper.userToUserDto(saveUser);
    }

    @Override
    public ResponseEntity<?> update(UserDto userDto) {
        boolean isExists = userDao.existsById(userDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("User not found"), HttpStatus.NOT_FOUND);
        }
        else{
            User user = userMapper.userDtoToUser(userDto);
            User updatedUser = userDao.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setActive(false);
        ResponseEntity<?> deletedUser = update(userMapper.userToUserDto(user));
        if (deletedUser.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("User not deleted"),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDto findById(Long id) {
        User user = userDao.findById(id).orElse(null);
        if(user != null) return userMapper.userToUserDto(user);
        return null;
    }
}
