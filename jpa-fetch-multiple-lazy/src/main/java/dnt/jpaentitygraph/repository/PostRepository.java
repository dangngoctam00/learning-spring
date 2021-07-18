package dnt.jpaentitygraph.repository;

import dnt.jpaentitygraph.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    @EntityGraph(attributePaths = {"comments", "images"})
    Optional<Post> findById(Long postId);
}
