public class Letter {

    public enum Status{NOT_GUESSED, NOT_IN_WORD, IN_WORD, IN_POSITION};
    public static final char FIRST_LETTER = 'A';
    public static final char LAST_LETTER = 'Z';
    
    private char letter;
    private Status status;

    public Letter(){
        this.letter = '_';
        this.status = Status.NOT_GUESSED;
    }

    public Letter(char letter){
        if(!Character.isUpperCase(letter) && letter != '_') {
            throw new IllegalArgumentException ("Invalid letter");
        }
        this.letter = letter;
        this.status = Status.NOT_GUESSED;
    }

    public char getLetter() {
        return this.letter;
    }
    
    public Status getStatus() {
        return this.status;
    }

    public void setLetter(char letter) {
        if(!Character.isUpperCase(letter) && letter != '_') {
            throw new IllegalArgumentException ("Invalid letter");
        }
        this.letter = letter;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
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

    @Override
    public String toString() {
        return this.letter + " " + this.status.name();
    }
}
