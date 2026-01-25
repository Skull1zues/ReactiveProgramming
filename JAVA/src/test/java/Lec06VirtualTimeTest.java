import com.soumya.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {

    private Flux<Integer> getNum(){
        return Flux.range(1,3)
                .delayElements(Duration.ofSeconds(5));
        // .map(i -> new Lec05AssertNextTest.Book(i, Util.faker().book().author(),Util.faker().book().title()));
    }

    @Test
    public void rangeTest1(){
        StepVerifier.create(getNum())
                .expectNext(1,2,3)
                .expectComplete()
                .verify();
    }

    @Test
    public void virtualTimerTest1(){
        StepVerifier.withVirtualTime(() -> getNum())
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1,2,3)
                .expectComplete()
                .verify();
    }

    @Test
    public void virtualTimerTest2(){
        StepVerifier.withVirtualTime(() -> getNum())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(20))
                .expectNext(2,3)
                .expectComplete()
                .verify();
    }
}
