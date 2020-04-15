import java.util.*;

public class Decoder {

    private static String dih = "";
    private static String dah = "";
    private static String altDih = "";
    private static String altDah = "";
    private static String spacer = "";
    private static String altSpacer = "";

    public static Integer howManyOfThese(String encoded, int position) {
        char indicated = encoded.charAt(position);
        int output = 0;
        for (int i = position; i < encoded.length(); i++) {
            if (encoded.charAt(i) == indicated) { output++;}
            else { break; }
        }
        return output;
    }

    public static MorseNotation getNote(int amt, char c, boolean flip) {
        StringBuilder note = new StringBuilder();
        for (int i = 0; i < amt; i++) { note.append(c); }
        if (!flip) {
            if (note.toString().equals(dih)) { return MorseNotation.DIH; }
            else if (note.toString().equals(dah)) { return MorseNotation.DAH; }
            else if (note.toString().equals(altDih)) { return MorseNotation.SKIP; }
            else if (note.toString().equals(altDah)) { return MorseNotation.DONE; }
            else if (note.toString().equals(spacer) || note.toString().equals(altSpacer)) { return MorseNotation.SPACE; }
        } else {
            if (note.toString().equals(altDih)) { return MorseNotation.DIH; }
            else if (note.toString().equals(altDah)) { return MorseNotation.DAH; }
            else if (note.toString().equals(dah)) { return MorseNotation.DONE; }
            else if (note.toString().equals(dih)) { return MorseNotation.SKIP; }
            else if (note.toString().equals(spacer) || note.toString().equals(altSpacer)) { return MorseNotation.SPACE; }
        }
        return MorseNotation.SPACE;
    }

    public static Set<String> getMagics(String encoded) {
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

    public static Integer getShortestLength(Collection<String> strings) {
        int lengthOfShortest = -1;
        for (String s : strings) {
            if (lengthOfShortest == -1) { lengthOfShortest = s.length(); }
            else if (s.length() < lengthOfShortest) { lengthOfShortest = s.length(); }
        }
        return lengthOfShortest;
    }

    public static void setDihs(Collection<String> strings, int lengthOfShortest) {
        dih = "";
        dah = "";
        for (String s : strings) {
            if (s.length() == lengthOfShortest) {
                if (!dih.equals("") && !s.equals(dih)) { altDih = s;  break; }
                else { dih = s; }
            }
        }
    }

    public static void setOtherVals() {
        dah = "";
        altDah = "";
        spacer = "";
        altSpacer = "";
        for (int i = 0; i < 3; i++) { dah += dih; }
        for (int i = 0; i < 3; i++) { altDah += altDih; }
        for (int i = 0; i < 7; i++) { spacer += dih; }
        for (int i = 0; i < 7; i++) { altSpacer += altDih; }
    }

    public static String recode(String encoded, TreeMap<Character, Integer> freq) {
        if (freq.keySet().size() > 2) {
            ArrayList<Character> goodGuys = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
                if (goodGuys.size() < 2) { goodGuys.add(entry.getKey()); }
                else { break; }
            }
            String newEncoded = "";
            for (int i = 0; i < encoded.length(); i++) {
                if (goodGuys.contains(encoded.charAt(i))) { newEncoded += encoded.charAt(i); }
                else {
                    for (int j = i+1; j < encoded.length(); j++) {
                        if (goodGuys.contains(encoded.charAt(j))) {
                            newEncoded += encoded.charAt(j);
                            break;
                        }
                    }
                }
            }
           return newEncoded;
        }
        return encoded;
    }

    public static String getNewEncoding(String encoding) {
        TreeMap<Character, Integer> freq = new TreeMap<>();
        for (char c : encoding.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { throw new IllegalArgumentException(); }
            else { freq.compute(c, (k, v) -> (v==null) ? 1 : v+1); }
        }
        return recode(encoding, freq);
    }

    public static String decode (String encoded) {
        encoded = getNewEncoding(encoded);
        Set<String> magicStrings = getMagics(encoded);
        Integer lengthOfShortest = getShortestLength(magicStrings);
        setDihs(magicStrings, lengthOfShortest);
        setOtherVals();
        ArrayList<MorseCharacter> phrase = new ArrayList<>();
        ArrayList<MorseNotation> ticks = new ArrayList<>();
        boolean flip = false;
        int iter = 0;
        while (iter < encoded.length()) {
            int hm = howManyOfThese(encoded, iter);
            MorseNotation nextNote = getNote(hm, encoded.charAt(iter), flip);
            if (nextNote == null) { throw new RuntimeException("nextNote came up null..."); }
            if (nextNote.equals(MorseNotation.DIH) || nextNote.equals(MorseNotation.DAH)) {
                ticks.add(nextNote);
            } else if (nextNote.equals(MorseNotation.DONE)) {
                Optional<MorseCharacter> toAdd = MorseCharactersArchive.getInstance().getCharacter(ticks);
                if (toAdd.isPresent()) {
                    phrase.add(toAdd.get());
                    ticks.clear();
                } else if (!flip) {
                    flip = true;
                    iter = 0;
                    ticks.clear();
                    phrase.clear();
                    continue;
                } else {
                    break;
                }
            } else if (nextNote.equals(MorseNotation.SPACE)) {
                Optional<MorseCharacter> finalChar = MorseCharactersArchive.getInstance().getCharacter(ticks);
                finalChar.ifPresent(phrase::add);
                if (phrase.size() > 0) { phrase.add(new MorseCharacter()); }
                ticks.clear();
            }
            iter += hm;
        }
        Optional<MorseCharacter> finalChar = MorseCharactersArchive.getInstance().getCharacter(ticks);
        finalChar.ifPresent(phrase::add);
        return new MorsePhrase(phrase).getOutput().trim();
    }



}
