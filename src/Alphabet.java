/**
 * Represents the alphabet of letters in the game. 
 * @author Amelia Saldino
 */
public class Alphabet {
    /** Max number of letter in alphabet */
    public static final int MAX_LETTERS = 26;

    /** Instance var array for letters in alphabet */
    private Letter[] letters;

    /**
     * Default constructor for alphabet
     */
    public Alphabet() {
        letters = new Letter[MAX_LETTERS];
        for(int i = 0; i < MAX_LETTERS; i++) {
            letters[i] = new Letter((char)(Letter.FIRST_LETTER + i));
        }
    }

    /**
     * Getter method for the letter array
     * @return array of letters
     */
    public Letter[] getLetters() {
        return letters;
    }

    /**
     * getter method for a specific letter object's char in the 
     * alphabet
     * @param index index of letter in arr
     * @return char of the letter object
     * @throws IllegalArguementException if index is invalid
     */
    public char getLetter(int index) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid index");
        }
        return letters[index].getLetter();
    }

    /**
     * getter method for a specific letter object's status in the 
     * alphabet
     * @param index index of letter in arr
     * @return status of the letter object
     * @throws IllegalArguementException if index is invalid
     */
    public Letter.Status getStatus(int index) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid index");
        }
        return letters[index].getStatus();
    }

    /**
     * update method for a given index and status
     * @param index index of letter to update
     * @param status new status
     * @throws IllegalArgumentException if index in invalid
     */
    public void updateStatus(int index, Letter.Status status) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid index");
        }
        if(letters[index].getStatus() == Letter.Status.IN_WORD && status == Letter.Status.IN_POSITION) {
            letters[index].setStatus(status);
        }
        if(letters[index].getStatus() == Letter.Status.NOT_GUESSED) {
            letters[index].setStatus(status);
        }
    }

    /**
     * Compares this alphabet object with another object for equality. Two alphabet objects are considered equal
     * if they have the same letters and the same statuses for each letter
     * 
     * @param o object to compare this alphabet to
     * @return true if the two alphabet objects are equal, true otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Alphabet a1 = (Alphabet)o;
        for(int i = 0; i < MAX_LETTERS; i++) {
            if(!this.letters[i].equals(a1.letters[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the alphabet, where each letter is printed on a new line with its status.
     * 
     * @return A string representation of the alphabet with each letter and its statu
     */
    @Override
    public String toString() {
        String total = "";
        for(int i = 0; i < MAX_LETTERS; i++) {
            total = total.concat(letters[i].toString() + "\n");
        }
        return total;
    }
}
