package sk.talos.service.impl;

import org.springframework.stereotype.Service;
import sk.talos.domain.post.PostDto;
import sk.talos.domain.post.UserDto;
import sk.talos.mapper.PostMapper;
import sk.talos.model.Post;
import sk.talos.repository.PostRepository;
import sk.talos.service.JsonPlaceholderUserService;
import sk.talos.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private JsonPlaceholderUserService jsonPlaceholderUserService;

    public PostServiceImpl(PostRepository postRepository, JsonPlaceholderUserService jsonPlaceholderUserService) {
        this.postRepository = postRepository;
        this.jsonPlaceholderUserService = jsonPlaceholderUserService;
    }

    @Override
    public Post createPost(PostDto postDto) {

        if (postDto == null) {
            throw new IllegalArgumentException("post_object_is_required");
        }

        if (postDto.getUserId() == null) {
            throw new IllegalStateException("post_user_id_is_mandatory");
        }

        UserDto user = jsonPlaceholderUserService.getUser(postDto.getUserId());

        if (user == null) {
            throw new IllegalStateException("post_user_does_not_exist");
        }

        return postRepository.save(PostMapper.INSTANCE.postDtoToPost(postDto));
    }

    @Override
    public Post getPost(Long postId) {

        if (postId == null) {
            throw new IllegalArgumentException("post_id_is_mandatory");
        }

        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getUserPosts(Long userId) {

        if (userId == null) {
            throw new IllegalArgumentException("user_id_is_mandatory");
        }

        return postRepository.findByUserId(userId);
    }

    @Override
    public Post updatePost(PostDto postDto) {

        if (postDto == null) {
            throw new IllegalArgumentException("post_is_mandatory");
        }

        Post post = this.getPost(postDto.getId());

        if (post == null) {
            throw new IllegalStateException("post_not_found");
        }

        PostMapper.INSTANCE.updatePostFromPostDto(post, postDto);

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {

        Post post = this.getPost(postId);

        if (post == null) {
            throw new IllegalStateException("post_does_not_exist");
        }

        postRepository.delete(post);
    }
}
