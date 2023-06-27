package sk.talos.service.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sk.talos.domain.post.PostDto;
import sk.talos.domain.post.UserDto;
import sk.talos.service.JsonPlaceholderPostService;

import java.io.IOException;
import java.net.URI;

@Service
public class JsonPlaceholderPostServiceImpl implements JsonPlaceholderPostService {


    private Environment env;


    public JsonPlaceholderPostServiceImpl(Environment env) {
        this.env = env;
    }

    @Override
    public PostDto getPost(Long postId) {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        ResponseEntity<PostDto> response = restTemplate
                .exchange(env.getProperty("com.typicode.host.url") + "/posts/" + postId,
                        HttpMethod.GET,
                        null,
                        PostDto.class);

        return response.getBody();
    }
}
