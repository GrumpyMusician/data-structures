
/**
 * This program demonstrates the LinkedList class
 * and ListIterator class.
*/

import java.util.LinkedList;

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
    }
}
