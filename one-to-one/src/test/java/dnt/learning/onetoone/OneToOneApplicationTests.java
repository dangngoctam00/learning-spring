package dnt.learning.onetoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class OneToOneApplicationTests {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuizResultRepository quizResultRepository;

	@Test
	void testLoad() {
		QuizEntity quizEntity = new QuizEntity();
		QuizResultEntity quizResultEntity = new QuizResultEntity();
		quizResultEntity.setQuiz(quizEntity);
		quizEntity.setResult(quizResultEntity);

		QuizEntity saved = quizRepository.save(quizEntity);

		Optional<QuizEntity> byId = quizRepository.findById(saved.getId());
		if (byId.isPresent()) {
			QuizEntity quizEntity1 = byId.get();
		}
//		Optional<QuizResultEntity> byId = quizResultRepository.findById(saved.getId());
//		if (byId.isPresent()) {
//			QuizResultEntity quizResultEntity1 = byId.get();
//		}
	}
}
