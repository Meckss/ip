import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Hope {
    private static final File data = new File("./data/Hope.txt");
    private static final TaskStorage taskStorage = new TaskStorage(data);
    private static boolean online = true;
    private static ToDoList toDoList = new ToDoList(taskStorage.toList()); {

    };

    private static final Set<String> COMMANDS = Set.of(
            "bye",
            "list",
            "mark",
            "unmark",
            "todo",
            "deadline",
            "event",
            "delete"
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
        EXECUTECOMMAND.put("delete", new deleteCommand());
    }

    interface Command {
        void execute(Object o);
    }

    static class endCommand implements Command {
        @Override
        public void execute(Object o) {
            System.out.println("Fare thee well, noble friend!");
            online = false;
        }
    }

    static class addTDTask implements Command{
        @Override
        public void execute(Object o) {
            String input = (String) o;
            if(input.isEmpty() || input.equals("todo")) {
                System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this endeavor!");
                System.out.println("(Empty input detected, please try again)\n");
                return;
            }
            ToDoTask temp = new ToDoTask(input);
            toDoList.add(temp);
            taskStorage.append(temp);
            System.out.println("Behold, this quest hath been entrusted!");
            System.out.println(temp.toString() + "\n");
            System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
            System.out.println("(You now have " + toDoList.size() + " tasks in the list)\n");
        }
    }

    static class addDTask implements Command{
        @Override
        public void execute(Object o) {
            String input = (String) o;
            if(input.equals("deadline")) {
                System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this endeavor!");
                System.out.println("(Looks like you forgot to input a description and a deadline! Try again)\n");
                return;
            }
            String[] info = input.split("/by");
            if(info.length == 1) {
                System.out.println("Verily, thou hast erred in thy response; endeavor once more, brave soul!");
                System.out.println("(Incorrect input, please try again)\n");
                return;
            }
            DeadlineTask temp = new DeadlineTask(info[0].trim(), info[1].trim());
            toDoList.add(temp);
            taskStorage.append(temp);
            System.out.println("Behold, this quest hath been entrusted!");
            System.out.println(temp.toString() + "\n");
            System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
            System.out.println("(You now have " + toDoList.size() + " tasks in the list)\n");
        }
    }

    static class addETask implements Command{
        @Override
        public void execute(Object o) {
            String input = (String) o;
            if(input.equals("event")) {
                System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this endeavor!");
                System.out.println("(Looks like you forgot to input description, from and to! Try again)\n");
                return;
            }
            String[] info = input.split("/from");
            if(info.length == 1) {
                System.out.println("Alas, fair traveler, thy input bears a flaw; kindly make haste and attempt anew!");
                System.out.println("(Incorrect input, please try again)\n");
                return;
            }
            String[] times = info[1].split("/to");
            if(times.length == 1) {
                System.out.println("Alas, fair traveler, thy input bears a flaw; kindly make haste and attempt anew!");
                System.out.println("(Incorrect input, please try again)\n");
                return;
            }
            EventTask temp = new EventTask(info[0].trim(), times[0].trim(), times[1].trim());
            toDoList.add(temp);
            taskStorage.append(temp);
            System.out.println("Behold, this quest hath been entrusted!:");
            System.out.println(temp.toString() + "\n");
            System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
            System.out.println("(You now have " + toDoList.size() + " tasks in the to do list)\n");
        }
    }

    static class listCommand implements Command {
        public void execute(Object o) {
            if(toDoList.size() == 0) {
                System.out.println("Thou art unburdened by quests in this moment, dear knight.\n"
                        + "(There are currently no tasks in your to do list)\n");
                return;
            }
            System.out.println("Hark! Behold the noble quests that grace thy scroll of undertakings!\n");
            System.out.println(toDoList.toString());
        }
    }

    static class markCommand implements Command {
        @Override
        public void execute(Object o) {
            if (o instanceof String) {
                try {
                    int input = Integer.parseInt((String) o);
                } catch (NumberFormatException e) {
                    System.out.println("Pray, employ the noble digits as thy guiding input!\n"
                            + "(Please use numerics as input only)\n");
                    return;
                }
                int input = Integer.parseInt((String) o);
                if(input > toDoList.size()) {
                    System.out.println("Thy request doth stray beyond the hallowed limits.\n"
                            + "(The number you have input is greater than the number of tasks you currently have)\n");
                    return;
                }
                if(input < 1) {
                    System.out.println("Doth this be a jest, good sir?\n"
                            + "(Negative numbers are not accepted as input)\n");
                    return;
                }
                try {
                    toDoList.get(input - 1).markAsDone();
                } catch (RedundantStateChangeException e) {
                    System.out.println("Verily, the noble quest was marked from the very outset; hath thy memory slipped away like a fleeting shadow?");
                    System.out.println("(The task was already marked to begin with)\n");
                    return;
                }
                taskStorage.update(toDoList);
                System.out.println("Hark, I declare the #" + input + " noble endeavor accomplished!");
                System.out.println(toDoList.get(input - 1) + "\n");
            }
        }
    }

    static class unmarkCommand implements Command {
        @Override
        public void execute(Object o) {
            if (o instanceof String) {
                try {
                    int input = Integer.parseInt((String) o);
                } catch (NumberFormatException e) {
                    System.out.println("Pray, employ the noble digits as thy guiding input!\n"
                            + "(Please use numerics as input only)\n");
                    return;
                }
                int input = Integer.parseInt((String) o);
                if(input > toDoList.size()) {
                    System.out.println("Thy request doth stray beyond the hallowed limits.\n"
                            + "(The number you have input is greater than the number of tasks you currently have)\n");
                    return;
                }
                if(input < 1) {
                    System.out.println("Doth this be a jest, good sir?\n"
                            + "(Negative numbers are not accepted as input)\n");
                    return;
                }
                try {
                    toDoList.get(input - 1).unmark();
                } catch (RedundantStateChangeException e) {
                    System.out.println("Verily, the noble quest was unmarked from the very outset; hath thy memory slipped away like a fleeting shadow?");
                    System.out.println("(The task was already unmarked to begin with)\n");
                    return;
                }
                taskStorage.update(toDoList);
                System.out.println("Lo! The noble quest, task #" + input + " , doth still beckon thy valorous attention!");
                System.out.println(toDoList.get(input - 1) + "\n");
            }
        }
    }

    static class deleteCommand implements Command{
        @Override
        public void execute(Object o) {
            if (o instanceof String) {
                try {
                    int input = Integer.parseInt((String) o);
                } catch (NumberFormatException e) {
                    System.out.println("Pray, employ the noble digits as thy guiding input!\n"
                            + "(Please use numerics as input only)\n");
                    return;
                }
                int input = Integer.parseInt((String) o);
                if (input > toDoList.size()) {
                    System.out.println("Thy request doth stray beyond the hallowed limits.\n"
                            + "(The number you have input is greater than the number of tasks you currently have)\n");
                    return;
                }
                if (input < 1) {
                    System.out.println("Doth this be a jest, good sir?\n"
                            + "(Negative numbers are not accepted as input)\n");
                    return;
                }


                System.out.println("Heed this decree! This noble quest hath been cast aside.");
                System.out.println(toDoList.get(input).toString());
                toDoList.remove(input);
                taskStorage.update(toDoList);
                System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
                System.out.println("(You now have " + toDoList.size() + " tasks in the to do list)\n");
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Greetings, fair wanderer! I am Hope, your humble companion\n" + "How might I assist thee on this grand quest?\n");
        Scanner userInput = new Scanner(System.in);
        while(online) {
            String input = userInput.nextLine();
            String[] instructions = input.split(" ", 2);
            String inputCommand = instructions[0];
            String argument = instructions.length == 2 ? instructions[1] : input;
            if(COMMANDS.contains(inputCommand)) {
                EXECUTECOMMAND.get(inputCommand).execute(argument.trim());
                continue;
            }
            System.out.println("Pardon me, noble companion, but what dost thou mean to convey?\n");
            continue;
        }

    }
}
