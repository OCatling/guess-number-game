package academy.learn.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // == fields ==
    private final int guessCount;
    private int number;
    private int smallest;
    private int largest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    @Setter
    private int guess;

    // == constructors ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount){
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = this.numberGenerator.getMinNumber();
        guess = this.numberGenerator.getMinNumber();
        remainingGuesses = this.guessCount;
        largest = this.numberGenerator.getMaxNumber();
        number = this.numberGenerator.next();
        log.debug("The number is {}", this.number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in game preDestroy()");
    }

    // == Public Methods ==
    @Override
    public void check(){
        checkValidNumberRange();
        if(validNumberRange){
            if(guess > number){
                largest = guess - 1;
            }

            if(guess < number){
                smallest = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == Provate Methods ==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest)  && (guess <= largest);
    }
}
