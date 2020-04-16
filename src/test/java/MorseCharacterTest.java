import junit.framework.TestCase;
import org.junit.*;

public class MorseCharacterTest extends TestCase {

    public void testTestHashCode() {
        MorseCharacter morse = new MorseCharacter();
        MorseCharacter morseB = new MorseCharacter();
        Integer hashMorse = morse.hashCode();
        Integer hashMorseB = morseB.hashCode();
        Assert.assertEquals(hashMorse, hashMorseB);
    }
}
