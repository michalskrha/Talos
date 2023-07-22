package sk.talos.service;

import sk.talos.domain.post.PostDto;

import java.util.Optional;

public interface JsonPlaceholderPostService {

    Optional<PostDto> getPost(Long postId);
}
