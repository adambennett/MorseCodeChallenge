import java.util.*;

public class MorseCharacter {

    private final ArrayList<MorseSound> ticks;
    private final Character output;

    public MorseCharacter(ArrayList<MorseSound> ticks, Character out) {
        this.ticks = ticks;
        this.output = out;
    }

    public MorseCharacter() {
        this.ticks = new ArrayList<>();
        this.output = ' ';
    }

    public Character getOutput() { return output; }

    public ArrayList<MorseSound> getTicks() { return this.ticks; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MorseCharacter)) return false;
        MorseCharacter that = (MorseCharacter) o;
        return Objects.equals(getTicks(), that.getTicks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicks());
    }
}
