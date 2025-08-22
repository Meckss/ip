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
        void execute();
    }

    static class endCommand implements Command {
        @Override
        public void execute() {
            System.out.println("See you soon!");
            online = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Hope \n" + "What can i do for you? \n");
        while(online) {
            Scanner userInput = new Scanner(System.in);
            String instruction = userInput.nextLine();
            if(COMMANDS.contains(instruction)) {
                EXECUTECOMMAND.get(instruction).execute();
                if(!online) {
                    break;
                }
            }
            System.out.println(instruction +"\n");
        }

    }
}
