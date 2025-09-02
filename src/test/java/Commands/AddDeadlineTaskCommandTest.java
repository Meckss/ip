package Commands;

import Storage.TaskStorage;
import Storage.ToDoList;
import Tasks.DeadlineTask;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddDeadlineTaskCommandTest {
    @Test
    public void execute_validInput_success() {
        String validInput = "test /by 02/03/2003";
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        TaskStorage taskStorage =  mock(TaskStorage.class);
        AddDeadlineTaskCommand addDeadlineTaskCommand = new AddDeadlineTaskCommand(toDoList, taskStorage);
        addDeadlineTaskCommand.execute(validInput);
        DeadlineTask expectedOutputToString = new DeadlineTask("test", "02/03/2003");
        assertEquals(expectedOutputToString.toString(), toDoList.get(1).toString(), "The added deadline task has the" +
                " correct description and deadline");
    }

    @Test
    public void execute_emptyArgsInput_printErrorMessage() {
        String emptyInput = "deadline";
        String expectedMessage = "Thou hast overlooked the noble task of bestowing a worthy description upon this " +
                "endeavor!\n(Looks like you forgot to input a description and a deadline! Try again)\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        TaskStorage taskStorage =  mock(TaskStorage.class);
        AddDeadlineTaskCommand addDeadlineTaskCommand = new AddDeadlineTaskCommand(toDoList, taskStorage);

        try {
            addDeadlineTaskCommand.execute(emptyInput);
            String actualOutput = outputStream.toString();
            assertEquals(expectedMessage.trim(), actualOutput.trim(), "The printed error message for negative input " +
                    "should match.");
        } finally {
            System.setOut(originalOutput);
        }
    }
    @Test
    public void execute_noDeadlineInput_printErrorMessage() {
        String noDeadlineInput = "deadline no by";
        String expectedMessage = "Verily, thou hast erred in thy response; endeavor once more, brave soul!\n" +
                "(Incorrect input, please try again)\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        TaskStorage taskStorage = mock(TaskStorage.class);
        AddDeadlineTaskCommand addDeadlineTaskCommand = new AddDeadlineTaskCommand(toDoList, taskStorage);

        try {
            addDeadlineTaskCommand.execute(noDeadlineInput);
            String actualOutput = outputStream.toString();
            assertEquals(expectedMessage.trim(), actualOutput.trim(), "The printed error message for negative input " +
                    "should match.");
        } finally {
            System.setOut(originalOutput);
        }
    }


}
