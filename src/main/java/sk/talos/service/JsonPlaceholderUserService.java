package sk.talos.service;

import sk.talos.domain.post.UserDto;

public interface JsonPlaceholderUserService {

    UserDto getUser(Long userId);
}
