package ngtam.learningspring.asycnannotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExecutorService {

    @Async("asyncExecutor")
    public void test() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("call execute");
    }
}
