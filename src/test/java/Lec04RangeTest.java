import com.soumya.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {

    private Flux<Integer> getItem(){
        return Flux.range(1,50)
                .map( i-> Util.faker().random().nextInt(1,100));
    }

    private Flux<Integer> getRandomItem(){
        return Flux.range(1,50);
    }

    @Test
    public void rengeTest1(){
        StepVerifier.create(getItem())
                .expectNext(1,2,3)
                .expectNextCount(47)
                .thenCancel()
                .verify();
    }

    @Test
    public void rengeTest2(){
        StepVerifier.create(getItem())
                .expectNext(1,2,3)
                .expectNextCount(26)
                .expectNext(30,31)
                .thenCancel()
                .verify();
    }
    @Test
    public void rengeTest3(){
        StepVerifier.create(getRandomItem())
                .expectNextMatches( i -> i>0 && i<101)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    public void rengeTest4(){
        StepVerifier.create(getRandomItem())
                .thenConsumeWhile(i ->  i>0 && i<101)
                .expectComplete()
                .verify();
    }
}
