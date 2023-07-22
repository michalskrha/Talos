package sk.talos.service;

import sk.talos.domain.post.UserDto;

import java.util.Optional;

public interface JsonPlaceholderUserService {

    Optional<UserDto> getUser(Long userId);
}
