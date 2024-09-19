import java.util.*;
import java.io.*;

public class StringLengthMap
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String filename = "C:\\Users\\thegr\\OneDrive\\Documents\\GitHub\\data-structures\\Chapter 15 Activities\\StringLengthMap\\src\\test1.txt";

        try (Scanner in = new Scanner(new File(filename)))
        {
            // Create your map here
            Map<Integer, Set<String>> lengths = new HashMap<>();

            while (in.hasNext())
            {
                String word = clean(in.next());
                Integer count = word.length();

                // Update the map here
                lengths.merge(count, new HashSet<>(Collections.singleton(word)), (existingSet, newSet) -> {
                    existingSet.addAll(newSet);
                    return existingSet;
                });
            }

            // Print the strings, in increasing order of their length
            lengths.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }
    }

    public static String clean(String s)
    {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (Character.isLetter(c))
            {
                r.append(c);
            }
        }
        return r.toString().toLowerCase();
    }
}
