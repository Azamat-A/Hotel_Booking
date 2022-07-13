package kg.megacom.hotel_booking.mappers;

import kg.megacom.hotel_booking.models.dtos.UserDto;
import kg.megacom.hotel_booking.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    List<User> userDtoListToUserList(List<UserDto> userDtos);

    List<UserDto> userListToUserDtoList(List<User> users);
}
