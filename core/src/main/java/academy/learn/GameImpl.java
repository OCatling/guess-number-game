package academy.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class GameImpl implements Game {

    // = Constants ==
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // == fields ==
    @Autowired
    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int largest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        guess = 0;
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
