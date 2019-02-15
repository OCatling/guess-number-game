package academy.learn.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GameImpl implements Game {

    // = Constants ==
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // == fields ==
    private final NumberGenerator numberGenerator;
    private final int guessCount;
    private int number;
    private int guess;
    private int smallest;
    private int largest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

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
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getLargest() {
        return largest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return this.guessCount;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
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
