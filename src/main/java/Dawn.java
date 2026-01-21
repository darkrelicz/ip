import java.util.Scanner;
import java.util.ArrayList;

public class Dawn {
    public static void main(String[] args) {
        ArrayList<String> db = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        System.out.println("""
        Hello! I'm Dawn!
        What can I do for you?
        """);

        boolean isBye = false;
        while (!isBye) {
            String text = s.nextLine();
            if (text.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                isBye = true;
            } else if (text.equals("list")) {
                for (int i = 0; i < db.size(); i++) {
                    String res = String.format("%d. %s", i+1, db.get(i));
                    System.out.println("\t"+res);
                }
            } 
            else {
                db.add(text);
                System.out.println("\tAdded: " + text);
            } 
        }        

        s.close();
    }
}
