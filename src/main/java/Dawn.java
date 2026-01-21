import java.util.Scanner;

public class Dawn {
    public static void main(String[] args) {
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
            } else {
                System.out.println("\tEcho: " + text);
            }
        }        

        s.close();
    }
}
