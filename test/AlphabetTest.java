import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//TODO: Add documentation to pass checkstyle

/**
 * Tests Alphabet class
 * 
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Amelia Saldino
 */
public class AlphabetTest {

    /** Alphabet for testing */
    private Alphabet alphabet;

    /**
     * Sets up field for testing
     */
    @BeforeEach
    public void setUp() {
        alphabet = new Alphabet();
    }

    /**
     * test getLetters after alphabet constructed
     */
    @Test
    public void testGetLetters() {
        Letter[] letters = alphabet.getLetters();
        for (int i = 0; i < 26; i++) {
            assertEquals(letters[i], new Letter((char)('A' + i)));
        }
    }
    
    
    /**
     * Test getStatus after alphabet constructed
     */
    @Test
    public void testGetStatusAfterConstructed() {
        for(int i = 0; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for all letters after construction");
        }
    }
    
    /**
     * Test getLetter after alphabet constructed
     */
    @Test
    public void testGetLetterAfterConstructed() {
        for(int i = 0; i < 26; i++) {
            assertEquals('A' + i, alphabet.getLetter(i), 
                         "Test getLetter for all letters after construction");
        }
    }    


    /**
     * Test updateStatus to IN_WORD when current status is NOT_GUESSED
     */
    @Test
    public void testUpdateStatusInWord() {
        alphabet.updateStatus(0, Letter.Status.IN_WORD);
        assertEquals(Letter.Status.IN_WORD, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.IN_WORD)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }
    

    /**
     * Test updateStatus to NOT_IN_WORD when current status is NOT_GUESSED
     */
    @Test
    public void testUpdateStatusNotInWord() {
        alphabet.updateStatus(0, Letter.Status.NOT_IN_WORD);
        assertEquals(Letter.Status.NOT_IN_WORD, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.NOT_IN_WORD)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
    }

    /**
     * Test updateStatus to IN_POSITION when current status is NOT_GUESSED
     */
    @Test
    public void testUpdateStatusInPosition() {
        // TODO: Write another test of update and getStatus
        // 1. Call updateStatus to change the status of letter[0] to IN_POSITION
        // 1. Write an assertEquals test for getStatus for letter[0]
        fail("No test added");
    } 
    

 
    /**
     * Test updateStatus to IN_POSITION when current status is IN_WORD
     */
    @Test
    public void testUpdateStatusInPositionAfterInWord() {
        alphabet.updateStatus(0, Letter.Status.IN_WORD);
        alphabet.updateStatus(0, Letter.Status.IN_POSITION);
        assertEquals(Letter.Status.IN_POSITION, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.IN_POSITION)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }    
    
        
    /**
     * Test status unchanged when current status is IN_WORD and updateStatus is NOT_GUESSED
     */
    @Test
    public void testUpdateStatusUnchangedInWordToNotGuessed() {
        alphabet.updateStatus(0, Letter.Status.IN_WORD);
        alphabet.updateStatus(0, Letter.Status.NOT_GUESSED);
        assertEquals(Letter.Status.IN_WORD, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.NOT_GUESSED)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }
    
    /**
     * Test status is unchanged when current status is IN_WORD and updateStatus is NOT_IN_WORD 
     */
    @Test
    public void testUpdateStatusUnchangedInWordToNotInWord() {
        // TODO: Write another test of update and getStatus
        // 1. Call updateStatus to change the status of letter[0] to IN_WORD
        // 2. Call updateStatus to change the status of letter[0] to NOT_IN_WORD        
        // 1. Write an assertEquals test for getStatus for letter[0]
        fail("No test added");              
    }    
    
    /**
     * Test status is unchanged when current status is NOT_IN_WORD and updateStatus is NOT_GUESSED 
     */
    @Test
    public void testUpdateStatusUnchangedNotInWordNotGuessed() {
        alphabet.updateStatus(0, Letter.Status.NOT_IN_WORD);
        alphabet.updateStatus(0, Letter.Status.NOT_GUESSED);
        assertEquals(Letter.Status.NOT_IN_WORD, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.NOT_GUESSED)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    } 
    
    /**
     * Test status is unchanged when current status is NOT_IN_WORD and updateStatus is IN_WORD 
     */
    @Test
    public void testUpdateStatusUnchangedNotInWordToInWord() {
        alphabet.updateStatus(0, Letter.Status.NOT_IN_WORD);
        alphabet.updateStatus(0, Letter.Status.IN_WORD);
        assertEquals(Letter.Status.NOT_IN_WORD, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.IN_WORD)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }

    /**
     * Test status is unchanged when current status is NOT_IN_WORD and updateStatus is IN_POSITION 
     */
    @Test
    public void testUpdateStatusUnchangedNotInWordToInPosition() {
        alphabet.updateStatus(0, Letter.Status.NOT_IN_WORD);
        alphabet.updateStatus(0, Letter.Status.IN_POSITION);
        assertEquals(Letter.Status.NOT_IN_WORD, alphabet.getStatus(0), 
                       "Test updateStatus(0, Letter.Status.IN_POSITION)");        
        for(int i = 1; i < 26; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }    

    /**
     * Test status is unchanged when current status IN_POSITION and updateStatus is NOT_GUESSED 
     */
    @Test
    public void testUpdateStatusUnchangedInPositionToNotGuessed() {
        alphabet.updateStatus(25, Letter.Status.IN_POSITION);
        alphabet.updateStatus(25, Letter.Status.NOT_GUESSED);
        assertEquals(Letter.Status.IN_POSITION, alphabet.getStatus(25), 
                       "Test updateStatus(25, Letter.Status.NOT_GUESSED)");        
        for(int i = 0; i < 25; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }  

    /**
     * Test status is unchanged when current status IN_POSITION and updateStatus is IN_WORD 
     */
    @Test
    public void testUpdateStatusUnchangedInPositionToInWord() {
        alphabet.updateStatus(25, Letter.Status.IN_POSITION);
        alphabet.updateStatus(25, Letter.Status.IN_WORD);
        assertEquals(Letter.Status.IN_POSITION, alphabet.getStatus(25), 
                       "Test updateStatus(25, Letter.Status.IN_WORD)");        
        for(int i = 0; i < 25; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    } 

    /**
     * Test status is unchanged when current status IN_POSITION and updateStatus is NOT_IN_WORD 
     */
    @Test
    public void testUpdateStatusUnchangedInPositionToNotInWord() {
        alphabet.updateStatus(25, Letter.Status.IN_POSITION);
        alphabet.updateStatus(25, Letter.Status.NOT_IN_WORD);
        assertEquals(Letter.Status.IN_POSITION, alphabet.getStatus(25), 
                       "Test updateStatus(25, Letter.Status.NOT_IN_WORD)");        
        for(int i = 0; i < 25; i++) {
            assertEquals(Letter.Status.NOT_GUESSED, alphabet.getStatus(i), 
                         "Test getStatus for letters not updated");
        }        
        
    }     


    /**
     * Test toString after constructor
     */
    @Test
    public void testToStringInitial() {
        String expected = "A NOT_GUESSED\n" + "B NOT_GUESSED\n" + "C NOT_GUESSED\n" +
                          "D NOT_GUESSED\n" + "E NOT_GUESSED\n" + "F NOT_GUESSED\n" +
                          "G NOT_GUESSED\n" + "H NOT_GUESSED\n" + "I NOT_GUESSED\n" +
                          "J NOT_GUESSED\n" + "K NOT_GUESSED\n" + "L NOT_GUESSED\n" +
                          "M NOT_GUESSED\n" + "N NOT_GUESSED\n" + "O NOT_GUESSED\n" +
                          "P NOT_GUESSED\n" + "Q NOT_GUESSED\n" + "R NOT_GUESSED\n" +
                          "S NOT_GUESSED\n" + "T NOT_GUESSED\n" + "U NOT_GUESSED\n" +
                          "V NOT_GUESSED\n" + "W NOT_GUESSED\n" + "X NOT_GUESSED\n" +
                          "Y NOT_GUESSED\n" + "Z NOT_GUESSED\n";
        assertEquals(expected, alphabet.toString(), "Test toString after constructing alphabet");
    }
    
    /**
     * Test toString after status changes
     */
    @Test
    public void testToStringMultipleStatus() {
        String expected = "A IN_WORD\n" + "B NOT_IN_WORD\n" + "C NOT_GUESSED\n" +
                          "D NOT_GUESSED\n" + "E NOT_GUESSED\n" + "F NOT_GUESSED\n" +
                          "G NOT_GUESSED\n" + "H NOT_GUESSED\n" + "I NOT_GUESSED\n" +
                          "J NOT_GUESSED\n" + "K NOT_GUESSED\n" + "L NOT_GUESSED\n" +
                          "M NOT_GUESSED\n" + "N NOT_GUESSED\n" + "O NOT_GUESSED\n" +
                          "P NOT_GUESSED\n" + "Q NOT_GUESSED\n" + "R NOT_GUESSED\n" +
                          "S NOT_GUESSED\n" + "T NOT_GUESSED\n" + "U NOT_GUESSED\n" +
                          "V NOT_GUESSED\n" + "W NOT_GUESSED\n" + "X NOT_GUESSED\n" +
                          "Y NOT_GUESSED\n" + "Z IN_POSITION\n";
                          
        alphabet.updateStatus(0, Letter.Status.IN_WORD);
        alphabet.updateStatus(1, Letter.Status.NOT_IN_WORD);
        alphabet.updateStatus(25, Letter.Status.IN_POSITION);        
        assertEquals(expected, alphabet.toString(), "Test toString after constructing alphabet");
    }    



    /**
     * Test equals for alphabet with itself
     */
    @Test
    public void testEqualsAlphabetWithItself() {
        assertTrue(alphabet.equals(alphabet), "Test equal alphabets");
    }
    
    /**
     * Test equals for alphabet with another alphabet object
     */
    @Test
    public void testEqualsWithEqualAlphabet() {
        Alphabet alphabet2 = new Alphabet();
        assertTrue(alphabet.equals(alphabet2), "Test equal alphabets");
    }

    /**
     * Test equals for alphabet with another alphabet with different statuses
     */    
    @Test
    public void testEqualsDifferentStatus() {
        // TODO: Write test for equals that would return false - alpabets with different 
        // statuses
        // 1. Create a new Alphabet (not alphabet)
        // 2. Call updateStatus to update the status of index 0 of the new alphabet to IN_WORD
        // 3. Write an assertFalse to test the equals method for alphabet and the new alphabet
        fail("No test added");        
    }                    
  
    
    /**
     * Test equals for alphabet with null
     */    
    @Test
    public void testEqualsNull() {
        assertFalse(alphabet.equals(null), "Test equals for alphabet with null ");
    }  

    /**
     * Test equals for alphabet with a String
     */    
    @Test
    public void testEqualsString() {
        assertFalse(alphabet.equals("alphabet"), "Test equals for alphabet with a String ");
    }    


    /**
     * Tests values for public constant
     */
    @Test
    public void testClassConstants() {
        assertEquals(26, Alphabet.MAX_LETTERS, "Test MAX_LETTERS constant");
    }
    

    /**
     * Test the Alphabet methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> alphabet.getLetter(26), 
            "alphabet.getLetter(26)");
        assertEquals("Invalid index", exception.getMessage(),
                     "Testing alphabet.getLetter(26) - exception message");
               
        exception = assertThrows(IllegalArgumentException.class,
            () -> alphabet.getLetter(-1), "alphabet.getLetter(-1)");
        assertEquals("Invalid index", exception.getMessage(),
                     "Testing alphabet.getLetter(-1) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> alphabet.getStatus(26), "alphabet.getStatus(26)");
        assertEquals("Invalid index", exception.getMessage(),
                     "Testing alphabet.getStatus(26) - exception message");                     
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> alphabet.getStatus(-1), "alphabet.getStatus(-1)");
        assertEquals("Invalid index", exception.getMessage(),
                     "Testing alphabet.getStatus(-1) - exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,             
            () -> alphabet.updateStatus(26, Letter.Status.IN_WORD ),
            "alphabet.updateStatus(26, Letter.Status.IN_WORD )");             
        assertEquals("Invalid index", exception.getMessage(),          
            "Testing alphabet.updateStatus(26, Letter.Status.IN_WORD ) - exception message");
            
        exception = assertThrows(IllegalArgumentException.class,
            () -> alphabet.updateStatus(-1, Letter.Status.IN_WORD ),
            "alphabet.updateStatus(-1, Letter.Status.IN_WORD )");
        assertEquals("Invalid index", exception.getMessage(),
            "Testing alphabet.updateStatus(-1, Letter.Status.IN_WORD ) - exception message");

    }    
}