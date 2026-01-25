import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec10TimeOutTest {
    private Flux<Integer> getNum(){
        return Flux.range(1,3)
                .delayElements(Duration.ofMillis(200));
        // .map(i -> new Lec05AssertNextTest.Book(i, Util.faker().book().author(),Util.faker().book().title()));
    }

    @Test
    public void timeOutTest(){
        StepVerifier.create(getNum())
                .expectNext(1,2,3)
                .expectComplete()
                .verify(Duration.ofMillis(1500));
    }
}
