import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {
    private static Mono<String> getUserName(int useerId) {
        return switch (useerId) {
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }
    @Test
    public void productTest(){
        StepVerifier.create(getUserName(2))
                //.expectNext("Sam")
                .expectComplete()
                .verify(); //subscribe
    }
    @Test
    public void errorTest(){
        StepVerifier.create(getUserName(3))
                //.expectNext("Sam")
                .expectError()
                .verify(); //subscribe
    }

    @Test
    public void errorTest1(){
        StepVerifier.create(getUserName(3))
                //.expectNext("Sam")
                .expectError(RuntimeException.class)
                .verify(); //subscribe
    }

    @Test
    public void errorTest2(){
        StepVerifier.create(getUserName(3))
                //.expectNext("Sam")
                .expectErrorMessage("Invalid input")
                .verify(); //subscribe
    }

    @Test
    public void errorTest3(){
        StepVerifier.create(getUserName(3))
                //.expectNext("Sam")
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("Invalid input", ex.getMessage());
                })
                .verify(); //subscribe
    }
}
