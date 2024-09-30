import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
/**
 * Write a program that checks whether a sequence of HTML tags
 * is properly nested. For each opening tag, such as <p>, there
 * must be a closing tag </p>. A tag such as <p> may have other
 * tags inside, for example <p> <ul> <li> </li> </ul> <a> </a> </p>
 * <p>
 * The inner tags must be closed before the outer ones.
 * Your program should process a file containing tags.
 * For simplicity, assume that the tags are separated by
 * spaces, and that there is no text inside the tags.
*/
public class HTMLChecker
{
    public static void main(String[] args)
    {
        String filename = "C:\\Users\\thegr\\OneDrive\\Documents\\GitHub\\data-structures\\Chapter 15 Activities\\HTMLChecker\\src\\TagSample3.html";

        try (Scanner in = new Scanner(new File(filename)))
        {
            // Your code goes here
            Stack<String> allTags = new Stack<>();
            Stack<String> expectedClosingTags = new Stack<>();

            boolean goodCode = true;

            String[] tagList = in.nextLine().split(" ");
            String currentTag = "";

            for (int i = 0; i < tagList.length; i++){
                allTags.push(tagList[i]);
            }

            int tagnum = allTags.size();
            for (int i = 0; i < tagnum; i++){
                currentTag = allTags.pop();
                if (currentTag.contains("/")){
                    expectedClosingTags.push("<" + currentTag.substring(2));
                }
                else {
                    if (!(expectedClosingTags.pop().equals(currentTag))){
                        goodCode = false;
                        break;
                    }
                }
            }

            if (goodCode)
                System.out.println("Good HTML Code.");
            else
                System.out.println("Bad HTML Code.");

        } catch (FileNotFoundException e)
        {
            System.out.println("Cannot open: " + filename);
        }

    }
}