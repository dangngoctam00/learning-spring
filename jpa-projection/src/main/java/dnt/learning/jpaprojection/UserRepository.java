package dnt.learning.jpaprojection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Query(value = "select distinct u.id as id, u.full_name as full_name, p.id as postId, p.date_created as date_created " +
//            "from users u left join post p on p.user_id = u.id " +
//            "where u.id = :id", nativeQuery = true)
//    List<UserPostDto> getUserAndPost(Long id);

    @Query(value = "select u.id as id, u.fullName as full_name, posts.id as postId, posts.dateCreated as date_created " +
            "from UserEntity u left join fetch PostEntity posts on posts.user.id = u.id " +
            "where u.id = :id")
    List<UserPostDto> getUserAndPost(Long id);

    @Query("select u from UserEntity u where u.id = :id")
    Optional<UserPostDto> getPostById(Long id);

//    @Query(value = "select u.id as id, u.fullName as full_name " +
//            "from UserEntity u " +
//            "where u.id = :id")
//    List<UserPostDto> getUserAndPost(Long id);
}
