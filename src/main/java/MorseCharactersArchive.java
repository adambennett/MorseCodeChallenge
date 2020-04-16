import java.util.*;

public class MorseCharactersArchive {

    private static final MorseCharactersArchive instance;
    private final ArrayList<MorseCharacter> allChars;

    private MorseCharactersArchive() {
        this.allChars = new ArrayList<>();
        initArchive();
    }

    public static MorseCharactersArchive getInstance() {
        return instance;
    }

    public Boolean exists(MorseCharacter test) {
        return this.allChars.contains(test);
    }

    public Optional<MorseCharacter> getCharacter(ArrayList<MorseSound> sounds) {
        MorseCharacter newChar = new MorseCharacter(sounds, null);
        if (exists(newChar)) {
            for (MorseCharacter morse : this.allChars) {
                if (morse.equals(newChar)) { return Optional.of(morse); }
            }
        }
        return Optional.empty();
    }

    static { try { instance = new MorseCharactersArchive(); } catch (Exception ex) { throw new RuntimeException("Failed to statically initialize MorseCharactersArchive!"); }}

    private void initArchive() {
        ArrayList<MorseSound> dynamicSounds = new ArrayList<>();
        MorseCharacter dynamicChar;

        ArrayList<MorseSound> dynamicSoundsA = new ArrayList<>();
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'A');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'B');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'C');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'D');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'E');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'F');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'G');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'H');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'I');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'J');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'K');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'L');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'M');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'N');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'O');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'P');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'Q');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'R');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'S');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'T');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'U');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'V');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'W');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'X');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'Y');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, 'Z');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, '1');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, '2');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, '3');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, '4');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, '5');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, '6');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, '7');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, '8');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DIH);
        dynamicChar = new MorseCharacter(dynamicSounds, '9');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();

        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicSounds.add(MorseSound.DAH);
        dynamicChar = new MorseCharacter(dynamicSounds, '0');
        this.allChars.add(dynamicChar); dynamicSounds = new ArrayList<>();
    }


}
