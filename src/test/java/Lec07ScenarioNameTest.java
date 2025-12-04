import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import java.time.Duration;

public class Lec07ScenarioNameTest {

    private Flux<Integer> getNum(){
        return Flux.range(1,3);
     }

    @Test
    public void rangeTest1(){
        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test");
        StepVerifier.create(getNum(),options)
                .expectNext(1,2)
                .as("first 2 item 1,3")
                .expectComplete()
                .verify();
    }
}
