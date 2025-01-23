import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    public static void main(String args[]) {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Jeff\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String exitMsg = "Bye. Hope to see you again soon!";
        String exitPhrase = "bye";
        String listPhrase = "list";

        ArrayList<String> list = new ArrayList<String>();

        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals(exitPhrase)) {
            String message = "";
            if (input.equals(listPhrase)) {
                String items = "";
                for (int i = 0; i < list.size(); i++) {
                    items += " " + (i + 1) + ". " + list.get(i) + "\n";
                }
                message = "____________________________________________________________\n" +
                        items +
                        "____________________________________________________________\n";
            } else {
                list.add(input);
                message = "____________________________________________________________\n" +
                        " added: " + input + "\n" +
                        "____________________________________________________________\n";
            }
            System.out.println(message);
            input = sc.nextLine();
        }

        String message = "____________________________________________________________\n" +
                " " + exitMsg + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }
}
