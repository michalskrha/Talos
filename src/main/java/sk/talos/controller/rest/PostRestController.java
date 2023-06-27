package sk.talos.controller.rest;

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
import sk.talos.service.JsonPlaceholderPostService;
import sk.talos.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/posts")
@Api(tags = {SwaggerTags.POSTS_TAG})
public class PostRestController {

    private PostService postService;
    private JsonPlaceholderPostService jsonPlaceholderPostService;

    public PostRestController(PostService postService, JsonPlaceholderPostService jsonPlaceholderPostService) {
        this.postService = postService;
        this.jsonPlaceholderPostService = jsonPlaceholderPostService;
    }


    @ApiOperation(value = "View a single user post.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user post."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @GetMapping(path = "/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId) {

        Post post = postService.getPost(postId);
        PostDto postDto = PostMapper.INSTANCE.postToPostDto(post);

        if (postDto == null) {
            postDto = jsonPlaceholderPostService.getPost(postId);
        }

        if (postDto == null) {
            return new ResponseEntity(new CommonResponseData<>(null,
                    new ResponseErrorData("Post not found.", HttpStatus.NOT_FOUND.getReasonPhrase())),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(new CommonResponseData(postDto), HttpStatus.OK);
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
            ResponseErrorData error = new ResponseErrorData(e.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
            return new ResponseEntity(new CommonResponseData(null, error), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(new CommonResponseData(PostMapper.INSTANCE.postToPostDto(post)), HttpStatus.CREATED);
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

        postDto.setId(postId);
        Post post = postService.updatePost(postDto);

        return new ResponseEntity(new CommonResponseData(PostMapper.INSTANCE.postToPostDto(post)), HttpStatus.OK);
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
        } catch (IllegalStateException e) {
            ResponseErrorData error = new ResponseErrorData(e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
            return new ResponseEntity(new CommonResponseData(null, error), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
