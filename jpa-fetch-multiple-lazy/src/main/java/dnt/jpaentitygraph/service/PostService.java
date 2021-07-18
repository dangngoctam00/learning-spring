package dnt.jpaentitygraph.service;

import dnt.jpaentitygraph.model.Post;
import dnt.jpaentitygraph.repository.PostRepository;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepo;
    private EntityManager entityManager;

    @Autowired
    public PostService(PostRepository postRepo, EntityManager entityManager) {
        this.postRepo = postRepo;
        this.entityManager = entityManager;
    }

    @Transactional
    public Post getPostById(Long postId) {
        List<Post> posts = entityManager.createQuery("select distinct p " +
                            "from Post p " +
                            "left join fetch p.comments " +
                            "where p.id = :postId", Post.class)
                .setParameter("postId", postId)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
        posts = entityManager.createQuery("select distinct p" +
                " from Post p" +
                " left join fetch p.images i" +
                " where p in :posts", Post.class)
                .setParameter("posts", posts)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
        Post post = posts.get(0);
        return post;
    }

    @Transactional
    public void doSomething(Long postId) {
        Post post = postRepo.getById(postId);
        post.setTitle("Change title :))");
    }
}
