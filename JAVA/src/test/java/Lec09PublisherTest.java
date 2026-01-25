import com.soumya.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {
    private UnaryOperator<Flux<String>> processor(){
        return stringFlux -> stringFlux
                .filter(s -> s.length()>1)
                .map(String::toUpperCase)
                .map(s -> s+": " +s.length());
    }

    @Test
    public void publisherTest1(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();


        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi","Hello"))
                .expectNext("HI: 2")
                .expectNext("HELLO: 5")
                .expectComplete()
                .verify();




    }
}
