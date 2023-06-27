package sk.talos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.talos.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);

}
