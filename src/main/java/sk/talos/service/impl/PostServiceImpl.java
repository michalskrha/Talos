package sk.talos.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sk.talos.domain.post.PostDto;
import sk.talos.mapper.PostMapper;
import sk.talos.model.Post;
import sk.talos.repository.PostRepository;
import sk.talos.service.JsonPlaceholderUserService;
import sk.talos.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final JsonPlaceholderUserService jsonPlaceholderUserService;

    public PostServiceImpl(PostRepository postRepository, JsonPlaceholderUserService jsonPlaceholderUserService) {
        this.postRepository = postRepository;
        this.jsonPlaceholderUserService = jsonPlaceholderUserService;
    }

    /**
     * Creates a Post from the PostDto.
     * @param postDto
     * @return
     */
    @Override
    public Post createPost(PostDto postDto) {
        validateBeforePost(postDto);
        validateUserInJsonPlaceholder(postDto.getUserId());

        return Optional.ofNullable(PostMapper.INSTANCE.postDtoToPost(postDto))
                .map(postRepository::save)
                .orElseThrow(() -> new IllegalStateException("post_cannot_be_created"));
    }


    /**
     * Gets a Post by its id.
     * @param postId
     * @return
     */
    @Override
    public Optional<Post> getPost(Long postId) {
        return Optional.ofNullable(postId)
                .flatMap(postRepository::findById);
    }

    /**
     * Gets all Posts.
     * @return
     */
    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }


    /**
     * Gets all Posts per page.
     * @return
     */
    @Override
    public Page<Post> getPosts(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest);
    }

    /**
     * Gets all Posts for a given user ID.
     * @param userId
     * @return
     */
    @Override
    public List<Post> getUserPosts(Long userId) {

        if (userId == null) {
            throw new IllegalArgumentException("user_id_is_mandatory");
        }

        return postRepository.findByUserId(userId);
    }

    /**
     * Updates a Post from the PostDto.
     * @param postDto
     * @return
     */
    @Override
    public Post updatePost(Long postId, PostDto postDto) {
        Post post = Optional.ofNullable(postDto)
                .map(dto -> this.getPost(dto.getId())
                        .orElseThrow(() -> new IllegalStateException("post_not_found")))
                .orElseThrow(() -> new IllegalArgumentException("post_is_mandatory"));

        PostMapper.INSTANCE.updatePostFromPostDto(post, postDto);
        return postRepository.save(post);
    }

    /**
     * Deletes a Post by its id.
     * @param postId
     */
    @Override
    public void deletePost(Long postId) {
        Post post = this.getPost(postId).orElseThrow(() -> new IllegalStateException("post_not_found"));
        postRepository.delete(post);
    }



    /**************************************** PRIVATE METHODS ****************************************/

    /**
     * Validates the PostDto before creating a Post.
     *
     * @param postDto
     */
    private void validateBeforePost(PostDto postDto) {
        if (postDto == null || postDto.getUserId() == null) {
            throw new IllegalArgumentException("post_and_user_id_are_required");
        }
    }
    /**
     * Validates if the user exists in the JsonPlaceholder service.
     *
     * @param userId
     */
    private void validateUserInJsonPlaceholder(Long userId) {
        jsonPlaceholderUserService.getUser(userId)
                .orElseThrow(() -> new IllegalStateException("user_does_not_exist_in_json_placeholder"));
    }

}
