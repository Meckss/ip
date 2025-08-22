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
            "unmark",
            "todo",
            "deadline",
            "event"
    );

    private static final HashMap<String, Command> EXECUTECOMMAND= new HashMap<>();

    static {
        EXECUTECOMMAND.put("bye", new endCommand());
        EXECUTECOMMAND.put("list", new listCommand());
        EXECUTECOMMAND.put("mark", new markCommand());
        EXECUTECOMMAND.put("unmark", new unmarkCommand());
        EXECUTECOMMAND.put("todo", new addTDTask());
        EXECUTECOMMAND.put("deadline", new addDTask());
        EXECUTECOMMAND.put("event", new addETask());
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

    static class addTDTask implements Command{
        @Override
        public void execute(Object o) {
            ToDoTask temp = new ToDoTask((String) o);
            toDoList.add(temp);
            System.out.println("Added this task:"+ "\n");
            System.out.println(temp.toString() + "\n");
            System.out.println("You now have " + toDoList.size() + " tasks in the list\n");
        }
    }

    static class addDTask implements Command{
        @Override
        public void execute(Object o) {
            String input = (String) o;
            if(input.equals("deadline")) {
                System.out.println("Looks like you forgot to input a description and a deadline! Try again\n");
                return;
            }
            String[] info = input.split("/by");
            if(info.length == 1) {
                System.out.println("Incorrect input, please try again\n");
                return;
            }
            DeadlineTask temp = new DeadlineTask(info[0].trim(), info[1].trim());
            toDoList.add(temp);
            System.out.println("Added this task:"+ "\n");
            System.out.println(temp.toString() + "\n");
            System.out.println("You now have " + toDoList.size() + " tasks in the list\n");
        }
    }

    static class addETask implements Command{
        @Override
        public void execute(Object o) {
            String input = (String) o;
            if(input.equals("event")) {
                System.out.println("Looks like you forgot to input description, from and to! Try again\n");
                return;
            }
            String[] info = input.split("/from");
            if(info.length == 1) {
                System.out.println("Incorrect input, please try again\n");
                return;
            }
            String[] times = info[1].split("/to");
            if(times.length == 1) {
                System.out.println("Incorrect input, please try again\n");
                return;
            }
            EventTask temp = new EventTask(info[0].trim(), times[0].trim(), times[1].trim());
            toDoList.add(temp);
            System.out.println("Added this task:"+ "\n");
            System.out.println(temp.toString() + "\n");
            System.out.println("You now have " + toDoList.size() + " tasks in the list\n");
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
                System.out.println(toDoList.get(input - 1) + "\n");
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


    public static void main(String[] args) {
        System.out.println("Hello! I'm Hope\n" + "What can i do for you?\n");
        Scanner userInput = new Scanner(System.in);
        while(online) {
            String input = userInput.nextLine();
            String[] instructions = input.split(" ", 2);
            String inputCommand = instructions[0];
            String argument = instructions.length == 2 ? instructions[1] : input;
            if(COMMANDS.contains(inputCommand)) {
                EXECUTECOMMAND.get(inputCommand).execute(argument);
                continue;
            }
            continue;
        }

    }
}
