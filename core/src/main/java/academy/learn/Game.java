package academy.learn;

public interface Game {

    void reset();

    void check();

    int getNumber();

    int getGuess();

    void setGuess(int guess);

    int getSmallest();

    int getLargest();

    int getRemainingGuesses();

    boolean isValidNumberRange();

    boolean isGameWon();

    boolean isGameLost();
}
