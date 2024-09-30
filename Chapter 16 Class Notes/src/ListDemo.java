/**
 *   A program that demonstrates the LinkedList class
 */
public class ListDemo
{
    public static void main(String[] args)
    {
        LinkedList students = new LinkedList();
        students.addFirst("Arthur");
        students.addFirst("Bijoux");
        students.addFirst("Jinan");
        students.addFirst("Andy");

        System.out.println(students);
    }
}
