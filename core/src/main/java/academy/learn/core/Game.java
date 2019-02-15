package academy.learn.core;

public interface Game {

    void reset();

    void check();

    int getNumber();

    int getGuess();

    void setGuess(int guess);

    int getSmallest();

    int getLargest();

    int getRemainingGuesses();

    int getGuessCount();

    boolean isValidNumberRange();

    boolean isGameWon();

    boolean isGameLost();
}
