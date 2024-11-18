

public class Alphabet {
    public static final int MAX_LETTERS = 26;

    private Letter[] letters;

    public Alphabet() {
        letters = new Letter[MAX_LETTERS];
        for(int i = 0; i < MAX_LETTERS; i++) {
            letters[i] = new Letter((char)(Letter.FIRST_LETTER + i));
        }
    }

    public Letter[] getLetters() {
        return letters;
    }

    public char getLetter(int index) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid index");
        }
        return letters[index].getLetter();
    }

    public Letter.Status getStatus(int index) {
        if(index < 0 || index >= 26) {
            throw new IllegalArgumentException("Invalid index");
        }
        return letters[index].getStatus();
    }

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

    @Override
    public String toString() {
        String total = "";
        for(int i = 0; i < MAX_LETTERS; i++) {
            total = total.concat(letters[i].toString() + "\n");
        }
        return total;
    }
}
