import java.util.Objects;
import java.util.Scanner;

public class Jeff {
    public static void main(String args[]) {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Jeff\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String exitMsg = "Bye. Hope to see you again soon!";
        String exitPhrase = "bye";

        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals(exitPhrase)) {
            String message = "____________________________________________________________\n" +
                        " " + input + "\n" +
                        "____________________________________________________________\n";
            System.out.println(message);
            input = sc.nextLine();
        }

        String message = "____________________________________________________________\n" +
                " " + exitMsg + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }
}
