package mconst.rpg.notification.services;

import lombok.extern.slf4j.Slf4j;
import mconst.rpg.notification.models.UserMapper;
import mconst.rpg.notification.models.dtos.UserDto;
import mconst.rpg.notification.models.entities.UserEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class UserService {
    private final String scheme;
    private final String host;
    private final String port;

    private final UserMapper userMapper;
    private final KeycloakTokenService keycloakTokenService;

    public UserService(UserMapper userMapper, KeycloakTokenService keycloakTokenService) {
        this.scheme = "http";
        this.host = "localhost";
        this.port = "8085";

        this.userMapper = userMapper;
        this.keycloakTokenService = keycloakTokenService;
    }

    private String getUrl(String path) {
        return scheme + "://" + host + ":" + port + path;
    }

    public UserEntity getUserById(String id) {
        var token = keycloakTokenService.getAccessToken().orElseThrow(() -> new RuntimeException("No keycloak token"));
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);
        var entity = new HttpEntity<>(headers);
        var userDto = restTemplate.exchange(getUrl("/users/" + id), HttpMethod.GET, entity, UserDto.class);
        return userMapper.map(userDto.getBody());
    }
}
