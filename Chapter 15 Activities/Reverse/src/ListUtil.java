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
        LinkedList<String> backwardList = new LinkedList<>();
        ListIterator<String> iterator = strings.listIterator();

        while (iterator.hasNext()) { // Put all the elements of strings into backwardList in reverse order
            String element = iterator.next();
            backwardList.addFirst(element);
        }

        while (strings.size() > 0) { // Get rid of all elements of strings to replace it with elements from backwardList
            strings.removeFirst();
        }

        for (int i = 0; i < backwardList.size(); i++) { // Putting all the backwardList elements into strings
            String element = backwardList.get(i);
            strings.add(element);
        }
        
    }
}