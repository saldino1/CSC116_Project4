import java.io.*;
import java.util.*;

/**
 * Represents the game of Wolfle, where the player guesses a secret word by submitting guesses
 * @author Amelia Saldino
 */
public class Wolfle {
    /**
     * Number of rows in game
     */
    public static final int ROWS = 6;

    /**
     * Number of cols in game
     */
    public static final int COLS = 5;

    /**
     * Instance var of alphabet
     */
    public Alphabet alphabet;

    /**
     * Instance var of grid
     */
    public Grid grid;

    /**
     * Instance var of storing game answer
     */
    public String secretWord;

    /**
     * boolean storing if the game is over because the 
     * player ansered correctly
     */

    public boolean gameIsOverCorrectGuess;
    /**
     * boolean storing if game is over because player ran out of guesses
     */
    public boolean gameIsOverNoMoreGuesses;

    /** A list of valid words that can be used as guesses. */
    public String[] validWords;

    /** A list of words from which the secret word can be chosen. */
    public String[] wolfleWords;

    /**
     * wolfle constructor loading in files
     * @param secretWordForTesting The secret word for testing purposes. If an empty string is provided, 
     *                             a random word will be chosen from the list of Wolfle words
     * @throws IllegalArgumentException if the provided secret word is not valid
     * @throws RuntimeException if there is an error reading the word lists from the files
     */
    public Wolfle(String secretWordForTesting) {
        alphabet = new Alphabet();
        grid = new Grid(ROWS, COLS);
        gameIsOverCorrectGuess = false;
        gameIsOverNoMoreGuesses = false;
        try (FileInputStream fis = new FileInputStream("word-files/ValidWordList.txt");
            Scanner scan = new Scanner(fis)){
            
            int wordCount = 0;
            while (scan.hasNextLine()) {
                scan.nextLine(); 
                wordCount++;
            }
            String[] words = new String[wordCount];

            fis.getChannel().position(0); 
            Scanner wordScanner = new Scanner(fis);

            int index = 0;
            while (wordScanner.hasNextLine()) {
                words[index] = wordScanner.nextLine().trim();
                index++;
            }
            validWords = words;
            wordScanner.close();
        }
        catch (FileNotFoundException e){
            throw new IllegalArgumentException("Unable to access file: word-files/ValidWordList.txt");

        }
        catch (IOException e) {
            throw new RuntimeException("Error reading file: " + "word-files/ValidWordList.txt", e);
        }

        try (FileInputStream fis = new FileInputStream("word-files/WolfleWordList.txt");
            Scanner scan = new Scanner(fis)){
            
            int wordCount = 0;
            while (scan.hasNextLine()) {
                scan.nextLine();  
                wordCount++;
            }
            String[] words = new String[wordCount];

            fis.getChannel().position(0); 
            Scanner wordScanner = new Scanner(fis);

            int index = 0;
            while (wordScanner.hasNextLine()) {
                words[index] = wordScanner.nextLine().trim();
                index++;
            }
            wolfleWords = words;
            wordScanner.close();
        }
        catch (FileNotFoundException e){
            throw new IllegalArgumentException("Unable to access file: word-files/WolfleWordList.txt");

        }
        catch (IOException e) {
            throw new RuntimeException("Error reading file: " + "word-files/WolfleWordList.txt", e);
        }
        
        if(secretWordForTesting.length() == 0) {
            Random rand = new Random();
            this.secretWord = wolfleWords[rand.nextInt(wolfleWords.length)];
        }
        else if (Arrays.asList(wolfleWords).contains(secretWordForTesting)) {
            this.secretWord = secretWordForTesting;   
        }
        else {
            throw new IllegalArgumentException("Invalid secret word");
        }
    }

     /**
     * Gets the secret word that needs to be guessed
     * @return The secret word
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Checks if the game is over due to a correct guess
     * @return true if the game is over due to a correct guess false otherwise
     */
    public boolean isGameOverCorrectGuess() {
        return gameIsOverCorrectGuess;
    }

    /**
     * Checks if the game is over because there are no more guesses left
     * @return true if the game is over due to no more guesses false otherwise
     */
    public boolean isGameOverNoMoreGuesses() {
        return gameIsOverNoMoreGuesses;
    }

    /**
     * Gets the status of a letter from the alphabet at the specified index
     * @param index The index of the letter in the alphabet
     * @return The status of the letter at the given index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public Letter.Status getLetterStatus(int index) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.alphabet.getStatus(index);
    }

    /**
     * Gets the status of a letter at a specific position in the guess grid
     * @param row The row index in the grid
     * @param col The column index in the grid
     * @return The status of the letter at the specified position in the grid
     * @throws IllegalArgumentException if the row or column index is out of bounds
     */
    public Letter.Status getGridLetterStatus(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid.getStatus(row, col);
    }

    /**
     * Evaluates the guessed word and returns an array of  objects representing the statuses of each letter
     * @param guess The guessed word
     * @return An array of objects representing the statuses of the letters in the guessed word
     *         Returns null if the guess is not valid
     * @throws IllegalArgumentException if the guess is null or not a valid word
     */
    public Letter[] evaluateGuess(String guess) {
        if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        }
        
        if (!Arrays.asList(validWords).contains(guess)) {
            return null; 
        }
        
        Letter[] letterStatuses = new Letter[COLS];
        
        for (int i = 0; i < COLS; i++) {
            letterStatuses[i] = new Letter(guess.charAt(i));
        }

        for (int i = 0; i < COLS; i++) {
            if (guess.charAt(i) == secretWord.charAt(i)) {
                letterStatuses[i].setStatus(Letter.Status.IN_POSITION);
            }
        }
        
        
        int[] secretWordAmountOfLetterOccurences = new int[26]; 

        for (int i = 0; i < COLS; i++) {
            if (letterStatuses[i].getStatus() == Letter.Status.IN_POSITION) {
                continue; 
            }
            // Clever way to get index of each letter by using char math :)
            secretWordAmountOfLetterOccurences[secretWord.charAt(i) - 'A']++;  
        }

        for (int i = 0; i < COLS; i++) {
            if (letterStatuses[i].getStatus() == Letter.Status.IN_POSITION) {
                continue;  
            }
            char current = guess.charAt(i);
            int index = current - 'A';

            if (secretWordAmountOfLetterOccurences[index] > 0) {
                letterStatuses[i].setStatus(Letter.Status.IN_WORD);
                secretWordAmountOfLetterOccurences[index]--;  
            } else {
                letterStatuses[i].setStatus(Letter.Status.NOT_IN_WORD);
            }
        }
        
        return letterStatuses;
    }

    /**
     * Processes the player's guess by updating the grid with letter statuses and checking if the game is over
     * @param guess The guessed word
     * @param gridRow The row in the grid to update with the guess
     * @return true if the guess was processed successfully, false if the guess was invalid
     * @throws IllegalArgumentException if the guess is null or the grid row is invalid
     */
    public boolean processGuess(String guess, int gridRow) {
        if (guess == null) {
            throw new IllegalArgumentException("Null guess");
        }
    
        if (gridRow < 0 || gridRow >= ROWS) {
            throw new IllegalArgumentException("Invalid grid row");
        }
    
        Letter[] letterStatuses = evaluateGuess(guess);
        if (letterStatuses == null) {
            return false;  
        }

        for (int col = 0; col < COLS; col++) {
            grid.updateLetter(gridRow, col, letterStatuses[col].getLetter(), letterStatuses[col].getStatus());
            for(int i = 0; i < alphabet.MAX_LETTERS; i ++){
                if(alphabet.getLetter(i) == guess.charAt(col)){
                    alphabet.updateStatus(i, letterStatuses[col].getStatus());
                }
            }
        }
    
        if (guess.equals(secretWord)) {
            this.gameIsOverCorrectGuess = true;
        } else if (gridRow == ROWS - 1) {
            this.gameIsOverNoMoreGuesses = true;
        }
    
        return true;
    }
    

}
