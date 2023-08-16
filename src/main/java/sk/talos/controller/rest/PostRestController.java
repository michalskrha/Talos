package sk.talos.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.talos.domain.http.response.CommonResponseData;
import sk.talos.domain.http.response.ResponseBuilder;
import sk.talos.domain.http.response.ResponseErrorData;
import sk.talos.domain.http.response.ResponseMetaData;
import sk.talos.domain.post.PostDto;
import sk.talos.enums.SwaggerTags;
import sk.talos.mapper.PostMapper;
import sk.talos.model.Post;
import sk.talos.service.JsonPlaceholderPostService;
import sk.talos.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
@Api(tags = {SwaggerTags.POSTS_TAG})
public class PostRestController {

    private final PostService postService;
    private final JsonPlaceholderPostService jsonPlaceholderPostService;

    public PostRestController(PostService postService, JsonPlaceholderPostService jsonPlaceholderPostService) {
        this.postService = postService;
        this.jsonPlaceholderPostService = jsonPlaceholderPostService;
    }


    @ApiOperation(value = "View a list of all user posts.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of user posts."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @GetMapping(path = "")
    public ResponseEntity<CommonResponseData> getPosts(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

        Page<Post> posts = postService.getPosts(PageRequest.of(page, count));
        List<PostDto> postDtoList = PostMapper.INSTANCE.postsToPostDtoList(posts.getContent());

        return ResponseBuilder.success(postDtoList, HttpStatus.OK, buildPagination(posts));
    }



    @ApiOperation(value = "View a single user post.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user post."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @GetMapping(path = "/{postId}")
    public ResponseEntity<CommonResponseData> getPost(@PathVariable Long postId) {

        PostDto postDto = postService.getPost(postId)
                .map(PostMapper.INSTANCE::postToPostDto)
                .orElseGet(() -> jsonPlaceholderPostService.getPost(postId).orElse(null));

        return postDto != null
                ? ResponseBuilder.success(postDto, HttpStatus.OK)
                : ResponseBuilder.error("Post not found.", HttpStatus.NOT_FOUND);
    }



    @ApiOperation(value = "Create a new post.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Post created."),
            @ApiResponse(code = 400, message = "You are tried invalid request body to the resource."),
    })
    @PostMapping(path = "")
    public ResponseEntity createPost(@Valid @RequestBody PostDto postDto) {

        Post post;
        try {
            post = postService.createPost(postDto);
        } catch (IllegalStateException e) {
            return ResponseBuilder.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseBuilder.success(PostMapper.INSTANCE.postToPostDto(post), HttpStatus.CREATED);
    }



    @ApiOperation(value = "Update an post by ID.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated post."),
            @ApiResponse(code = 400, message = "You are tried invalid request body to the resource."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @PutMapping(path = "/{postId}")
    public ResponseEntity updateCustomer(@PathVariable Long postId,
                                         @Valid @RequestBody PostDto postDto) {

        Post post = postService.updatePost(postId, postDto);
        PostDto createdPostDto = PostMapper.INSTANCE.postToPostDto(post);
        return ResponseBuilder.success(createdPostDto, HttpStatus.OK);
    }



    @ApiOperation(value = "Delete an post by ID.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Post was successfully deleted."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @DeleteMapping(path = "/{postId}")
    public ResponseEntity deleteCustomer(@PathVariable Long postId) {

        try {
            postService.deletePost(postId);
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            ResponseErrorData error = new ResponseErrorData(e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
            return new ResponseEntity(new CommonResponseData(null, error), HttpStatus.NOT_FOUND);
        }
    }


    private ResponseMetaData buildPagination(Page<Post> posts) {
        return ResponseMetaData.builder()
                .total(posts.getTotalElements())
                .currentPage(posts.getNumber())
                .build();
    }

}
