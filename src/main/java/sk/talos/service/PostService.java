package sk.talos.service;

import sk.talos.domain.post.PostDto;
import sk.talos.model.Post;
import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto);

    Post getPost(Long postId);

    List<Post> getPosts();

    List<Post> getUserPosts(Long userId);

    Post updatePost(PostDto postDto);

    void deletePost(Long postId);
}
