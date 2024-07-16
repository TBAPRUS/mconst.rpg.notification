package mconst.rpg.notification.models;

import mconst.rpg.notification.models.dtos.UserDto;
import mconst.rpg.notification.models.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity map(UserDto userDto);
    UserDto map(UserEntity userEntity);
}
