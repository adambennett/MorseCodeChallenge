import java.util.*;

public class MorsePhrase {

    private String output;

    public MorsePhrase(ArrayList<MorseCharacter> sounds) {
       this.output = "";
       for (MorseCharacter c : sounds) {
           this.output += c.getOutput();
       }
    }

    public String getOutput() { return output; }
}
