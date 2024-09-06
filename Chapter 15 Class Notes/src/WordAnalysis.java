import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This program checks which words in a file are not present in a dictionary.
*/
public class WordAnalysis
{
    public static void main(String[] args)
        throws FileNotFoundException
    {
        // Read the dictionary file and the novel file
        Set <String> dictionaryWords = readWords("C:\\Users\\aliang\\Desktop\\APCS\\data-structures\\Chapter 15 Class Notes\\src\\words");
        Set <String> bookWords = readWords("Chapter 15 Class Notes/src/war-and-peace.txt");
   
        // Print all words in novel
        for (String word: novelWords){
            if (!dictionaryWords.contains(word)){
                System.out.print(word + " ");
            }
        }
    }

    /**
     * Reads all words from a file.
     *
     * @param filename the name of the file
     * @return a set with all lowercased words in the file. Here, a
     * word is a sequence of upper- and lowercase letters.
    */
    public static Set<String> readWords(String filename)
        throws FileNotFoundException
    {
        Set<String> words = new HashSet<>();

        Scanner in = new Scanner(new File(filename), "UTF-8");

        // Use any character that is not a letter as a delimeter
        in.useDelimiter("[^a-zA-Z]+");

        while (in.hasNext()) {
            // Add words to set, duplicates ignored.
            words.add(in.next().toLowerCase());
        }

        return null;
    }
}
