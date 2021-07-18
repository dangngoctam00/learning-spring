package dnt.jpaentitygraph;

import dnt.jpaentitygraph.model.Comment;
import dnt.jpaentitygraph.model.Image;
import dnt.jpaentitygraph.model.Post;
import dnt.jpaentitygraph.repository.CommentRepository;
import dnt.jpaentitygraph.repository.ImageRepository;
import dnt.jpaentitygraph.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
public class JpaEntityGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEntityGraphApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(PostRepository postRepo,
                               CommentRepository commentRepo,
                               ImageRepository imageRepo) {
        return args -> {
            Post post = new Post();
            post.setTitle("Manchester city");

            Comment comment = new Comment();
            comment.setContent("This is football club");
            Comment comment1 = new Comment();
            comment1.setContent("It looks pretty good!");

            comment.setPost(post);
            comment1.setPost(post);

            post.setComments(Arrays.asList(comment, comment1));

            Image image = new Image();
            image.setUrl("https://image.com");

            Image image2 = new Image();
            image2.setUrl("https://image.com");

            post.setImages(Arrays.asList(image));
            image.setPosts(Arrays.asList(post));

            /*
            *  object of entity which does not own relation is saved first.
            * */
            imageRepo.save(image);
            imageRepo.save(image2);

            postRepo.save(post);
            commentRepo.save(comment);
            commentRepo.save(comment1);

        };
    }
}
