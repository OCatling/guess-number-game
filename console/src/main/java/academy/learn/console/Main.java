package academy.learn.console;

import academy.learn.core.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        // Create Context (Container)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        // Close context
        context.close();
    }
}
