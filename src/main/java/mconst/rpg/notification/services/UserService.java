package mconst.rpg.notification.services;

import mconst.rpg.notification.models.UserMapper;
import mconst.rpg.notification.models.dtos.UserDto;
import mconst.rpg.notification.models.entities.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private String scheme;
    private String host;
    private String port;

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.scheme = "http";
        this.host = "localhost";
        this.port = "8085";

        this.userMapper = userMapper;
    }

    private String getUrl(String path) {
        return scheme + "://" + host + ":" + port + path;
    }

    public UserEntity getUserById(Long id) {
        var restTemplate = new RestTemplate();
        var userDto = restTemplate.getForObject(getUrl("/users/" + id), UserDto.class);
        return userMapper.map(userDto);
    }
}
