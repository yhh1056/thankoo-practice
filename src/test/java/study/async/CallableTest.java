package study.async;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallableTest {
    private final Logger log = LoggerFactory.getLogger(CallableTest.class);

    @Test
    void 비동기_반환() throws ExecutionException, InterruptedException {
        log.info("start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        FactorialTask task = new FactorialTask(5);

        Future<Integer> future = executorService.submit(task);

        assertThat(future.get().intValue()).isEqualTo(120);
    }

    @Test
    void 비동기에서_예외가_발생해도_메인_스레드에서_예외_처리를_못_한다() throws ExecutionException, InterruptedException {
        log.info("start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        FactorialTask task = new FactorialTask(-1);

        assertThatNoException()
                .isThrownBy(() -> executorService.submit(task));
    }
}

class FactorialTask implements Callable<Integer> {
    private final Logger log = LoggerFactory.getLogger(FactorialTask.class);

    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws RuntimeException {
        log.info("hello");

        if (number < 0) {
            throw new RuntimeException("Number should be positive");
        }
        int fact = 1;
        for (int count = number; count > 1; count--) {
            fact = fact * count;
        }

        return fact;
    }
}