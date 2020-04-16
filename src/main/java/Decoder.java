import java.util.*;
import java.util.stream.*;

public class Decoder {

    private String dih;
    private String dah;
    private String altDih;
    private String altDah;
    private String spacer;
    private String altSpacer;
    private static final Decoder decoder;

    private Decoder() { reset(); }

    private void reset() {
        this.dih = "";
        this.dah = "";
        this.altDih = "";
        this.altDah = "";
        this.spacer = "";
        this.altSpacer = "";
    }

    public static String decode (String encoded) {
        decoder.reset();
        encoded = decoder.getNewEncoding(encoded);
        Set<String> magicStrings = decoder.magicSubstrings(encoded);
        decoder.setDihs(magicStrings);
        decoder.setOtherVals();
        ArrayList<MorseCharacter> phrase = new ArrayList<>();
        ArrayList<MorseSound> ticks = new ArrayList<>();
        boolean flip = false;
        for (int i = 0; i < encoded.length();) {
            int charOccurences = decoder.countOccurencesUntilNewChar(encoded, i);
            MorseSound nextSound = decoder.getNote(charOccurences, encoded.charAt(i), flip);

            // Dih or Dah
            if (nextSound.equals(MorseSound.DIH) || nextSound.equals(MorseSound.DAH)) { ticks.add(nextSound); }

            // Space or Unidentified
            else if (nextSound.equals(MorseSound.SPACE)) {
                Optional<MorseCharacter> finalChar = MorseCharactersArchive.getInstance().getCharacter(ticks);
                finalChar.ifPresent(phrase::add);
                if (phrase.size() > 0) { phrase.add(new MorseCharacter()); }
                ticks.clear();
            }

            // End of Character
            else if (nextSound.equals(MorseSound.DONE)) {
                Optional<MorseCharacter> toAdd = MorseCharactersArchive.getInstance().getCharacter(ticks);
                if (toAdd.isPresent()) {
                    phrase.add(toAdd.get());
                    ticks.clear();
                } else if (!flip) {
                    flip = true;
                    i = 0;
                    ticks.clear();
                    phrase.clear();
                    continue;
                } else {
                    break;
                }
            }
            i += charOccurences;
        }
        Optional<MorseCharacter> finalChar = MorseCharactersArchive.getInstance().getCharacter(ticks);
        finalChar.ifPresent(phrase::add);
        return new MorsePhrase(phrase).getOutput().trim();
    }

    private Integer countOccurencesUntilNewChar(String encoded, int position) {
        char indicated = encoded.charAt(position);
        int output = 0;
        for (int i = position; i < encoded.length(); i++) {
            if (encoded.charAt(i) == indicated) { output++;}
            else { break; }
        }
        return output;
    }

    private MorseSound getNote(int amtInSequence, char seq, boolean flip) {
        StringBuilder note = new StringBuilder();
        for (int i = 0; i < amtInSequence; i++) { note.append(seq); }
        if (!flip) {
            if (note.toString().equals(dih)) { return MorseSound.DIH; }
            else if (note.toString().equals(dah)) { return MorseSound.DAH; }
            else if (note.toString().equals(altDih)) { return MorseSound.SKIP; }
            else if (note.toString().equals(altDah)) { return MorseSound.DONE; }
            else if (note.toString().equals(spacer) || note.toString().equals(altSpacer)) { return MorseSound.SPACE; }
        } else {
            if (note.toString().equals(altDih)) { return MorseSound.DIH; }
            else if (note.toString().equals(altDah)) { return MorseSound.DAH; }
            else if (note.toString().equals(dah)) { return MorseSound.DONE; }
            else if (note.toString().equals(dih)) { return MorseSound.SKIP; }
            else if (note.toString().equals(spacer) || note.toString().equals(altSpacer)) { return MorseSound.SPACE; }
        }
        return MorseSound.SPACE;
    }

    private Set<String> magicSubstrings(String encoded) {
        Set<String> magicStrings = new HashSet<>();
        char current = encoded.charAt(0);
        String curr = "" + current;
        int iter = 1;
        while (iter < encoded.length()) {
            if (encoded.charAt(iter) == current) { curr += encoded.charAt(iter); }
            else {
                magicStrings.add(curr);
                current = encoded.charAt(iter);
                curr = "" + current;
            }
            iter++;
        }
        magicStrings.add(curr);
        return magicStrings;
    }

    private Integer shortestOf(Collection<String> strings) {
        return strings.stream().min(Comparator.comparing(String::length)).get().length();
    }

    private void setDihs(Collection<String> strings) {
        int lengthOfShortest = shortestOf(strings);
        for (String s : strings) {
            if (s.length() == lengthOfShortest) {
                if (!dih.equals("") && !s.equals(dih)) { altDih = s;  break; }
                else { dih = s; }
            }
        }
    }

    private void setOtherVals() {
        for (int i = 0; i < 3; i++) { dah += dih; }
        for (int i = 0; i < 3; i++) { altDah += altDih; }
        for (int i = 0; i < 7; i++) { spacer += dih; }
        for (int i = 0; i < 7; i++) { altSpacer += altDih; }
    }

    private String recode(String encoded, TreeMap<Character, Integer> freq) {
        if (freq.keySet().size() > 2) {
            List<Character> actualEncodingChars = freq.keySet().stream().limit(2).collect(Collectors.toList());
            StringBuilder newEncoded = new StringBuilder();
            for (int i = 0; i < encoded.length(); i++) {
                if (actualEncodingChars.contains(encoded.charAt(i))) { newEncoded.append(encoded.charAt(i)); }
                else {
                    for (int j = i+1; j < encoded.length(); j++) {
                        if (actualEncodingChars.contains(encoded.charAt(j))) {
                            newEncoded.append(encoded.charAt(j));
                            break;
                        }
                    }
                }
            }
           return newEncoded.toString();
        }
        return encoded;
    }

    private String getNewEncoding(String encoding) {
        TreeMap<Character, Integer> freq = new TreeMap<>();
        for (char c : encoding.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { throw new IllegalArgumentException(); }
            else { freq.compute(c, (k, v) -> (v==null) ? 1 : v+1); }
        }
        return recode(encoding, freq);
    }

    static { try { decoder = new Decoder(); } catch(Exception ex) { throw new RuntimeException("Failed to initialize Decoder!"); }}

}
