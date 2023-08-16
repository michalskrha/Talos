package sk.talos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import sk.talos.domain.post.PostDto;
import sk.talos.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    /**
     * Creates a new post.
     *
     * @param postDto The DTO containing post data.
     * @return The created post.
     */
    Post createPost(PostDto postDto);

    /**
     * Retrieves a post by its ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return An optional containing the post if found.
     */
    Optional<Post> getPost(Long postId);

    /**
     * Retrieves a list of all posts.
     *
     * @return The list of posts.
     */
    List<Post> getPosts();

    /**
     * Retrieves a list of all posts.
     *
     * @return The list of posts.
     */
    Page<Post> getPosts(PageRequest pageRequest);

    /**
     * Retrieves a list of posts belonging to a specific user.
     *
     * @param userId The ID of the user.
     * @return The list of user's posts.
     */
    List<Post> getUserPosts(Long userId);

    /**
     * Updates an existing post.
     *
     * @param postId  The ID of the post to update.
     * @param postDto The DTO containing updated post data.
     * @return The updated post.
     */
    Post updatePost(Long postId, PostDto postDto);

    /**
     * Deletes a post by its ID.
     *
     * @param postId The ID of the post to delete.
     */
    void deletePost(Long postId);
}
