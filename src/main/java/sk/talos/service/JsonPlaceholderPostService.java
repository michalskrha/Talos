package sk.talos.service;

import sk.talos.domain.post.PostDto;

public interface JsonPlaceholderPostService {

    PostDto getPost(Long postId);
}
