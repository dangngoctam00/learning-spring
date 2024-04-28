package dnt.learning.jpaprojection;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPostDto {

    Long getId();
    String getFull_name();
    List<PostDto> getPosts();
}