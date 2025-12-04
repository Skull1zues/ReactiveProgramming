import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {

    private Flux<Integer> getItem(){
        return Flux.just(1,2,3)
                .log();
    }

    @Test
    public void fluxTest1(){
        StepVerifier.create(getItem(),1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    public void fluxTest2(){
        StepVerifier.create(getItem())
                .expectNext(1,2,3)
                .thenCancel()
                .verify();
    }
}
