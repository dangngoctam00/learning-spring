package dnt.learning.jpaprojection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
class JpaProjectionApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Test
	@Transactional
	void testLoad() {
		UserEntity user = new UserEntity();
		user.setFullName("abcxyz");
		PostEntity post1 = new PostEntity();
		post1.setDateCreated(LocalDateTime.now());
		PostEntity post2 = new PostEntity();
		post2.setDateCreated(LocalDateTime.now());

		user.addPost(post1);
		user.addPost(post2);

		userRepository.saveAndFlush(user);

//		List<UserPostDto> userAndPost = userRepository.getUserAndPost(1L);
//		assertEquals(1, userAndPost.get(0).getId());
		Optional<UserPostDto> userEntityById = userRepository.getPostById(1L);
		var x = 5;
	}

	@Test
	@Transactional
	void testBuildCriteria() {
		postRepository.findByCustom();
	}
}
