import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;


/**
 * A program that implements the sieve of Eratosthenes.
*/
public class Sieve
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Compute primes up to which integer? ");
        int n = in.nextInt();

        // Your work goes here
        Set<Integer> allnums = new HashSet<>();

        for (int i = 1; i <= n; i++){
            allnums.add(i);
        }

        for (int i = 2; i <= n; i++){
            for (int j = 2; j <= n; j++){
                allnums.remove(i*j); // I'm suprised this part worked first try :p
            }
                
        }

        allnums.remove(1); // 1 can not be a prime number, since prime numbers require exactly two factors.

        System.out.println("Your prime numbers are: " + allnums);

    }
}
