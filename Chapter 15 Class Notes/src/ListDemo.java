
/**
 * This program demonstrates the LinkedList class
 * and ListIterator class.
*/

import java.util.LinkedList;
import java.util.ListIterator;

public class ListDemo
{
    public static void main(String[] args)
    {
        /* addLast method to populate a list*/
        LinkedList<String> staff = new LinkedList<>();
        staff.addLast("Tony");
        staff.addLast("Natasha");
        staff.addLast("Peter");
        staff.addLast("Gemora");

        System.out.println(staff);

        /* The list is currently Tony Natasha Peter Gemora */

        /*
         * The ListIterator method creates a new list iterator that is positioned at the head of the list 
         * The | symbol will be used to represent the iterator position
         */

        ListIterator<String> iterator = staff.listIterator(); // | Tony Natasha Peter Gemora

        /* The "next()" method advances the iterator over the next element in the list. It can also return the element just before the iterator. */
        iterator.next(); // Tony | Natasha Peter Gemora

        String avenger = iterator.next(); // Tony (Natasha) | Peter Gemora
        System.out.println(avenger);

        /* The "add()" method inserts an element at the iterator position. The iterator will then be positioned after the element added. */
        iterator.add("Steve"); // Tony Natasha Steve | Peter Gemora
        iterator.add("Clint"); // Tony Natasha Steve Clint | Peter Gemora
        System.out.println(staff);

        /* The "remove()" method removes the element by the last call by "next()" or "previous()". It can only be called after calling "next()" or "previous()", but not "add()" */
        iterator.next(); // Tony Natasha Steve Peter | Gemora
        iterator.remove(); // Tony Natasha Steve | Gemora
        System.out.println(staff);

        /* The "set()" method updates the element returned by the last call to "next()" or "previous()" */
        iterator.previous(); // Tony Natasha Steve | Clint Gemora
        iterator.set("T'Challa"); // Tony Natasha Steve | T'Challa Gemora

        /* The "hasNext()" method is used to determine if there is a next node, i.e. if the iterator is at the end of the list or not */
        iterator = staff.listIterator(); // | Tony Natasha Steve T'Challa Gemora
        while (iterator.hasNext()) {
            String n = iterator.next();
            if (n.equals("Natasha")) // Tony Natasha | Steve T'Challa Gemora
                iterator.remove(); // Tony | Steve T'Challa Gemora
        } // Tony Steve T'Challa Gemora |

        /* Enhanced "for" loops work with linked lists. It automatically creats an iterator. */
        for (String n: staff){
            System.out.print(n + " ");
        }
        System.out.println();

        /* You can not modify a linked list while using an iterator unless if you use the iterator to do the modification. */
        iterator = staff.listIterator(); // | Tony Steve T'Challa Gemora
        while (iterator.hadNext()) {
            String n = iterator.next();
            if (n.equals("Tony")){
                // staff.remove("Tony"); // Dis is de problem.
            }
        }

    }
}
