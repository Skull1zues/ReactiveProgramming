import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx ->{

            if(ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("user"));
            }else {
                return Mono.error(new RuntimeException("Not Authenticated"));
            }
        });
    }

    @Test
    public void welcomeMessageTest(){
        var option = StepVerifierOptions.create().withInitialContext(Context.of("user","sam"));
        StepVerifier.create(getWelcomeMessage(),option)
                .expectNext("Welcome sam")
                .expectComplete()
                .verify();
    }
}
