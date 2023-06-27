package sk.talos.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.talos.enums.SwaggerTags;

@RestController
@RequestMapping(path = "/api/users")
@Api(tags = {SwaggerTags.POSTS_TAG})
public class PostController  {
}
