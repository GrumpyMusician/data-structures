import java.util.*;

/**
 * A program to add, remove, modify or print
 * student names and grades.
*/
public class Gradebook
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        Map<String, String> grades = new HashMap<>();

        boolean done = false;
        while(!done)
        {
            System.out.println("A)dd R)emove M)odify P)rint Q)uit");
            String input = in.next().toUpperCase();


            if (input.equals("Q")){
                done = true;
            } else if (input.equals("A") || input.equals("M")){
                System.out.println("Name: ");
                String name = in.next();

                System.out.println("Grade: ");
                String grade = in.next();
                grades.put(name, grade);  

            } else if (input.equals("R")){
                System.out.println("Name: ");
                String name = in.next();
                grades.remove(name);

            } else if (input.equalsIgnoreCase("P"))
            {

                Set<String> keys = grades.keySet();
                ArrayList<String> names = new ArrayList<>();
                for (String key: keys){
                    names.add(key);
                }

                Collections.sort(names);

                for (int i = 0; i < names.size(); i++){
                    System.out.println(names.get(i) + ": " + grades.get(names.get(i)));
                }

            } else
            {
                done = true;
            }
        }
    }
}
