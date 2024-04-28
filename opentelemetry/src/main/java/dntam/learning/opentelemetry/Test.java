package dntam.learning.opentelemetry;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class Test {

    private final MeterRegistry meterRegistry;
    private ScheduledExecutorService executor;

    @GetMapping("")
    public void endpoint() {
        log.info("[dntam] Log test");
    }

    @PostConstruct
    public void start() {
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            meterRegistry.counter("dntam.test-metric", "username", "dntam").increment();
        }, 0, 15, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void end() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
