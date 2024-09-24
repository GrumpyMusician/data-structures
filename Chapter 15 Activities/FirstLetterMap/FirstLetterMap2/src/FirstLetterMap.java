import java.util.*;
import java.io.*;

/**
 * Read all words from a file and add them to a map
 * whose keys are the first letters of the words and
 * whose values are sets of words that start with
 * that same letter. Then print out the word sets in
 * alphabetical order. Update the map by modifying
 * Worked Example 15.1.
 */
public class FirstLetterMap {
    public static void main(String[] args) {
        String filename = "C:\\Users\\thegr\\OneDrive\\Documents\\GitHub\\data-structures\\Chapter 15 Activities\\FirstLetterMap\\FirstLetterMap2\\src\\test1.txt";

        try (Scanner in = new Scanner(new File(filename))) {
            // Create your map here
            Map<Character, Set<String>> letterMap = new TreeMap<>();

            while (in.hasNext()) {
                String word = clean(in.next());
                if (word.isEmpty()) continue;
                Character c = word.charAt(0);

                // Update the map here
                letterMap.putIfAbsent(c, new TreeSet<>());
                letterMap.get(c).add(word);
            }

            // Print the map here in this form
            // a: [a, able, aardvark]
            for (Map.Entry<Character, Set<String>> entry : letterMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + filename);
        }
    }

    public static String clean(String s) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                r.append(c);
            }
        }
        return r.toString().toLowerCase();
    }
}
