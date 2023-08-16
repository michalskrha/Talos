package sk.talos.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import sk.talos.domain.post.UserDto;
import sk.talos.service.JsonPlaceholderUserService;

import java.util.Optional;

@Service
public class JsonPlaceholderUserServiceImpl implements JsonPlaceholderUserService {

    private final String tipicodeHostUrl;

    public JsonPlaceholderUserServiceImpl(@Value("${com.typicode.host.url}") String tipicodeHostUrl) {
        this.tipicodeHostUrl = tipicodeHostUrl;
    }

    @Override
    public Optional<UserDto> getUser(Long userId) {
        validateUserId(userId);

        String apiUrl = tipicodeHostUrl + "/users/" + userId;
        try {
            UserDto userDto = new RestTemplate().getForObject(apiUrl, UserDto.class);
            return Optional.ofNullable(userDto);
        } catch (RestClientException ex) {
            // Handle the exception appropriately, e.g., log it or throw a custom exception.
            return Optional.empty();
        }
    }

    private void validateUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
    }
}
