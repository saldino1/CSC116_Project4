/**
 * Represents a letter in the game with a specific status.
 * @author Amelia Saldino
 */
public class Letter {
    /**
     * Enum representing state of letter
     */
    public enum Status{NOT_GUESSED, NOT_IN_WORD, IN_WORD, IN_POSITION};

    /** The First letter in the alphabet*/
    public static final char FIRST_LETTER = 'A';

    /** The Last letter in the alphabet */
    public static final char LAST_LETTER = 'Z';
    
    /** 
     * Instance var represening the char letter that 
     * the object is representing
     */
    private char letter;

    /**
     * Instance var representing status of the letter
     * relative to the wolfle word
     */
    private Status status;

    /**
     * Blank constructor that initialized as '_'
     */
    public Letter(){
        this.letter = '_';
        this.status = Status.NOT_GUESSED;
    }

    /**
     * Constructor Overload that sets the character specifically
     * @param letter
     * @throws IllegalArguementException if letter is invalid
     */
    public Letter(char letter){
        if(!Character.isUpperCase(letter) && letter != '_') {
            throw new IllegalArgumentException ("Invalid letter");
        }
        this.letter = letter;
        this.status = Status.NOT_GUESSED;
    }

    /**
     * getter method for letter var
     * @return letter
     */
    public char getLetter() {
        return this.letter;
    }
    
    /**
     * getter method for status enum
     * @return status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * setter method for instance var of letter
     * @param letter letter to be set to
     * @throws IllegalArgumentException if letter being set in invalid
     */
    public void setLetter(char letter) {
        if(!Character.isUpperCase(letter) && letter != '_') {
            throw new IllegalArgumentException ("Invalid letter");
        }
        this.letter = letter;
    }

    /**
     * setter method for status
     * @param status status to be set to
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * Compares this letter to another object. Two letters are considered equal if they have the same
     * character and status.
     * @param o The object to compare this letter to.
     * @return true if the letters are equal and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Letter l1 = (Letter)o;
        return this.letter == l1.letter && this.status == l1.status;
    }

    /**
     * Returns a string representation of this letter in the format: letter status
     * @return string representation of the letter and its status
     */
    @Override
    public String toString() {
        return this.letter + " " + this.status.name();
    }
}
