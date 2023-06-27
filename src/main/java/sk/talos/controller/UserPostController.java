package sk.talos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.talos.domain.http.response.CommonResponseData;
import sk.talos.domain.http.response.ResponseErrorData;
import sk.talos.domain.post.PostDto;
import sk.talos.enums.SwaggerTags;
import sk.talos.mapper.PostMapper;
import sk.talos.model.Post;
import sk.talos.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users/{userId}/posts")
@Api(tags = {SwaggerTags.USER_POSTS_TAG})
public class UserPostController {

    private PostService postService;

    public UserPostController(PostService postService) {
        this.postService = postService;
    }


    @ApiOperation(value = "View a single user post.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user post."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @GetMapping(path = "")
    public ResponseEntity getUserPost(@PathVariable Long userId) {

        List<Post> userPosts = postService.getUserPosts(userId);

        List<PostDto> postDto = PostMapper.INSTANCE.postsToPostDtoList(userPosts);

        return new ResponseEntity(new CommonResponseData(postDto), HttpStatus.OK);
    }



}
