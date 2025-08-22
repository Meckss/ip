import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Hope {

    private static boolean online = true;
    private static List<Task> toDoList = new ArrayList<>() {
        @Override
        public String toString() {
            if(isEmpty()) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < size(); i++) {
                sb.append(i + 1).append(".").append(get(i).toString()).append("\n");
            }
            return sb.toString();
        };
    };

    private static final Set<String> COMMANDS = Set.of(
            "bye",
            "list",
            "mark",
            "unmark"
    );

    private static final HashMap<String, Command> EXECUTECOMMAND= new HashMap<>();

    static {
        EXECUTECOMMAND.put("bye", new endCommand());
        EXECUTECOMMAND.put("list", new listCommand());
        EXECUTECOMMAND.put("mark", new markCommand());
        EXECUTECOMMAND.put("unmark", new unmarkCommand());
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
            toDoList.add(new Task((String)o));
            System.out.println("added: " + (String)o + "\n");
        }
    }

    static class listCommand implements Command {
        public void execute(Object o) {
            System.out.println(toDoList.toString());
        }
    }

    static class markCommand implements Command {
        @Override
        public void execute(Object o) {
            // assume for now that valid input always, will handle exceptions later on
            if (o instanceof String) {
                int input = Integer.parseInt((String) o);
                toDoList.get(input - 1).markAsDone();
                System.out.println("Alrighty, marked #" + input + " as done\n");
                System.out.println(toDoList.get(input - 1));
            }
        }
    }

    static class unmarkCommand implements Command {
        @Override
        public void execute(Object o) {
            // assume for now that valid input always, will handle exceptions later on
            if (o instanceof String) {
                int input = Integer.parseInt((String) o);
                toDoList.get(input - 1).unmark();
                System.out.println("Alrighty, marked #" + input + " as undone\n");
                System.out.println(toDoList.get(input - 1) + "\n");
            }
        }
    }

    static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "X" : " ";
        }

        protected void markAsDone() {
            this.isDone = true;
        }

        protected void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(this.getStatusIcon()).append("] ").append(this.description);
            return sb.toString();
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
                continue;
            }

            new addCommand().execute(input);
        }

    }
}
