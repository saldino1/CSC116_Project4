import java.io.*;
import java.util.*;

public class Wolfle {
    public final int ROWS = 6;
    public final int COLS = 5;
    public Alphabet alphabet;
    public Grid grid;
    public String secretWord;
    public boolean gameIsOverCorrectGuess;
    public boolean gameIsOverNoMoreGuesses;
    public String[] validWords;
    public String[] wolfleWords;

    public Wolfle(String secretWordForTesting) {
        alphabet = new Alphabet();
        grid = new Grid(ROWS, COLS);
        gameIsOverCorrectGuess = false;
        gameIsOverNoMoreGuesses = false;
        try {
            Scanner scan = new Scanner(new FileInputStream("word-files/ValidWordList.txt"));
            while(scan.hasNextLine()) {
                Scanner in = new Scanner(scan.nextLine());
                
            }
        }
        catch (FileNotFoundException e){
            throw new IllegalArgumentException("Unable to access file: word-files/ValidWordList.txt");

        }
    }
}
