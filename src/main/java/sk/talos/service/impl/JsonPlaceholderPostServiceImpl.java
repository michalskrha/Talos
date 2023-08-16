package sk.talos.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import sk.talos.domain.post.PostDto;
import sk.talos.service.JsonPlaceholderPostService;

import java.util.Optional;

@Service
public class JsonPlaceholderPostServiceImpl implements JsonPlaceholderPostService {

    private final String tipicodeHostUrl;

    public JsonPlaceholderPostServiceImpl(@Value("${com.typicode.host.url}") String tipicodeHostUrl) {
        this.tipicodeHostUrl = tipicodeHostUrl;
    }

    @Override
    public Optional<PostDto> getPost(Long postId) {
        validatePostId(postId);

        String apiUrl = tipicodeHostUrl + "/posts/" + postId;
        try {
            PostDto postDto = new RestTemplate().getForObject(apiUrl, PostDto.class);
            return Optional.ofNullable(postDto);
        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }

    private void validatePostId(Long postId) {
        if (postId == null) {
            throw new IllegalArgumentException("post_id_is_mandatory");
        }
    }
}
