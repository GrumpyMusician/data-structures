import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class supplies a utility method to reverse the entries in a linked list.
*/
public class ListUtil{
    /**
     * Reverses the elements in a linked list
     *
     * @param strings the linked list to reverse
    */
    public static void reverse(LinkedList<String> strings)
    {
        int total = 0;
        String elemshift;
        
        ListIterator<String> iterator = strings.listIterator();

        while (iterator.hasNext())
            total ++; // Finds the length of the list, puts the iterator at the back of the list.
        
        for (int i = 0; i <= total; i++){
            iterator.previous();
            elemshift = iterator.next();
            iterator.remove();
            strings.addFirst(elemshift);
        }
    }
}