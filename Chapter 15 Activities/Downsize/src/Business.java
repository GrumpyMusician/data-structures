import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Business utility methods.
*/
public class Business
{
    /**
      * Removes every nth element from the linked list
      *
      * @param employeeNames the linked list to remove from
      * @param n                 the parameter to determine "nth"
     */
    public static void downsize(LinkedList<String> employeeNames, int n)
    {
        ListIterator<String> iterator = employeeNames.listIterator();
        iterator = employeeNames.listIterator();


        for ( int i = 0; iterator.hasNext(); i++)
        {
            iterator.next();
            if ((i+1) % n == 0)
                iterator.remove();
        }

    }
}