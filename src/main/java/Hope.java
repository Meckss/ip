import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Hope {

    private static boolean online = true;
    private static List<String> toDoList = new ArrayList<>();
    private static final Set<String> COMMANDS = Set.of(
            "bye"
    );

    private static final HashMap<String, Command> EXECUTECOMMAND= new HashMap<>();

    static {
        EXECUTECOMMAND.put("bye", new endCommand());
    }

    interface Command {
        void execute(Object o);
    }

    static class endCommand implements Command {
        @Override
        public void execute(Object o) {
            System.out.println("See you soon!");
            online = false;
        }
    }

    static class addCommand implements Command {
        public void execute(Object o) {
            // assume for now that valid input always, will handle exceptions later on
            toDoList.add((String)o);
            System.out.println("added: " + (String)o + "\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Hope \n" + "What can i do for you? \n");
        while(online) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            String[] instructions = input.split(" ", 2);
            String inputCommand = instructions[0];
            String argument = instructions.length == 2 ? instructions[1] : input;
            if(COMMANDS.contains(inputCommand)) {
                EXECUTECOMMAND.get(inputCommand).execute(argument);
                break;
            }

            new addCommand().execute(input);
        }

    }
}
