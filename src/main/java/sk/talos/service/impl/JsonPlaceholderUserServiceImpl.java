package sk.talos.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.talos.domain.post.UserDto;
import sk.talos.service.JsonPlaceholderUserService;

import java.util.Optional;

@Service
public class JsonPlaceholderUserServiceImpl implements JsonPlaceholderUserService {

    @Value("${com.typicode.host.url}")
    private String tipicodeHostUrl;

    @Override
    public Optional<UserDto> getUser(Long userId) {
        validateUserId(userId);
        UserDto userDto = new RestTemplate().getForObject(tipicodeHostUrl + "/users/" + userId, UserDto.class);
        return Optional.ofNullable(userDto);
    }

    private void validateUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("user_id_is_mandatory");
        }
    }
}
