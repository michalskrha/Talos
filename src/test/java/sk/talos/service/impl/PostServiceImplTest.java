package sk.talos.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.talos.domain.post.PostDto;
import sk.talos.domain.post.UserDto;
import sk.talos.model.Post;
import sk.talos.repository.PostRepository;
import sk.talos.service.JsonPlaceholderUserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private JsonPlaceholderUserService jsonPlaceholderUserService;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatePost_UserExists() {
        // GIVEN
        // Create a sample PostDto
        PostDto postDto = new PostDto();
        postDto.setTitle("title");
        postDto.setBody("body");
        postDto.setUserId(1L);

        // Mock getUser method to return a non-empty Optional
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(jsonPlaceholderUserService.getUser(postDto.getUserId())).thenReturn(Optional.of(userDto));

        // Mock postRepository.save method to return a Post
        Post post = new Post();
        post.setId(1L);
        post.setTitle("title");
        post.setBody("body");
        when(postRepository.save(any())).thenReturn(post);

        // WHEN
        // Call the createPost method
        Post result = postService.createPost(postDto);

        // THEN
        // Verify that getUser and save methods are called with the correct parameters
        verify(jsonPlaceholderUserService).getUser(postDto.getUserId());
        verify(postRepository).save(any());

        // Ensure that the result is not null
        assertNotNull(result);
    }

    @Test
    void testCreatePost_UserDoesNotExist() {
        // GIVEN
        // Create a sample PostDto
        PostDto postDto = new PostDto();
        postDto.setTitle("title");
        postDto.setBody("body");
        postDto.setUserId(1L);

        // Mock getUser method to return an empty Optional
        when(jsonPlaceholderUserService.getUser(postDto.getUserId())).thenReturn(Optional.empty());

        // WHEN / THEN
        // Call the createPost method and expect an IllegalStateException to be thrown
        assertThrows(IllegalStateException.class, () -> postService.createPost(postDto));

        // Verify that getUser is called with the correct parameter
        verify(jsonPlaceholderUserService).getUser(postDto.getUserId());
    }

    @Test
    void testGetPost_PostIdIsNotNull() {

        // Mock postRepository.findById method to return a non-empty Optional
        Post post = new Post();
        post.setId(1L);
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));

        // Call the getPost method
        Optional<Post> result = postService.getPost(post.getId());

        // Verify that findById is called with the correct parameter
        verify(postRepository).findById(post.getId());

        // Ensure that the result is not null
        assertNotNull(result);
    }

}
