package sk.talos.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.talos.domain.post.PostDto;
import sk.talos.service.JsonPlaceholderPostService;

import java.util.Optional;

@Service
public class JsonPlaceholderPostServiceImpl implements JsonPlaceholderPostService {

    @Value("${com.typicode.host.url}")
    private String tipicodeHostUrl;

    @Override
    public Optional<PostDto> getPost(Long postId) {
        validatePostId(postId);

        PostDto postDto = new RestTemplate().getForObject(tipicodeHostUrl + "/posts/" + postId, PostDto.class);
        return Optional.ofNullable(postDto);
    }

    private void validatePostId(Long postId) {
        if (postId == null) {
            throw new IllegalArgumentException("post_id_is_mandatory");
        }
    }
}
