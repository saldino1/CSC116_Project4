import java.io.*;
import java.util.*;

public class Wolfle {
    public static final int ROWS = 6;
    public static final int COLS = 5;
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
        try (FileInputStream fis = new FileInputStream("word-files/ValidWordList.txt");
            Scanner scan = new Scanner(fis)){
            
            int wordCount = 0;
            while (scan.hasNextLine()) {
                scan.nextLine();  // Just read and discard each line
                wordCount++;
            }
            String[] words = new String[wordCount];

            fis.getChannel().position(0); // Reset file pointer to the beginning of the file
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
                scan.nextLine();  // Just read and discard each line
                wordCount++;
            }
            String[] words = new String[wordCount];

            fis.getChannel().position(0); // Reset file pointer to the beginning of the file
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

    public String getSecretWord() {
        return secretWord;
    }

    public boolean isGameOverCorrectGuess() {
        return gameIsOverCorrectGuess;
    }

    public boolean isGameOverNoMoreGuesses() {
        return gameIsOverNoMoreGuesses;
    }

    public Letter.Status getLetterStatus(int index) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid Index");
        }
        return this.alphabet.getStatus(index);
    }

    public Letter.Status getGridLetterStatus(int row, int col) {
        if (row < 0 || row >= ROWS) {
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= COLS) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid.getStatus(row, col);
    }

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
        
        
        int[] secretWordLetterCount = new int[26]; 

        for (int i = 0; i < COLS; i++) {
            if (letterStatuses[i].getStatus() == Letter.Status.IN_POSITION) {
                continue; 
            }
            secretWordLetterCount[secretWord.charAt(i) - 'A']++;  // Count occurrences of each letter in secretWord
        }

        for (int i = 0; i < COLS; i++) {
            if (letterStatuses[i].getStatus() == Letter.Status.IN_POSITION) {
                continue;  
            }
            char currentChar = guess.charAt(i);
            int charIndex = currentChar - 'A';

            if (secretWordLetterCount[charIndex] > 0) {
                letterStatuses[i].setStatus(Letter.Status.IN_WORD);
                secretWordLetterCount[charIndex]--;  
            } else {
                letterStatuses[i].setStatus(Letter.Status.NOT_IN_WORD);
            }
        }
        
        return letterStatuses;
    }

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
