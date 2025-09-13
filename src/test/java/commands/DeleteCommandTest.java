package commands;

import hope.commands.DeleteCommand;
import hope.storage.TaskStorage;
import hope.storage.ToDoList;
import hope.tasks.ToDoTask;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

public class DeleteCommandTest {
    @Test
    public void execute_negativeNumberInput_errorMessagePrinted() {
        String negativeNumberInput = "-1";
        String expectedMessage = "Doth this be a jest, good sir?\n(0 and negative numbers are not accepted as input)\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        TaskStorage taskStorage =  mock(TaskStorage.class);
        DeleteCommand deleteCommand = new DeleteCommand(toDoList, taskStorage);

        try {
            deleteCommand.execute(negativeNumberInput);
            String actualOutput = outputStream.toString();
            assertEquals(expectedMessage.trim(), actualOutput.trim(), "The printed error message for negative input should match.");
            verifyNoInteractions(taskStorage);
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    public void execute_greaterThanCurrentListSizeInput_errorMessagePrinted() {
        String invalidInput = "2";
        String expectedMessage = "Thy request doth stray beyond the hallowed limits.\n(The number you have input is greater than the number of tasks you currently have)\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        TaskStorage taskStorage = mock(TaskStorage.class);
        DeleteCommand deleteCommand = new DeleteCommand(toDoList, taskStorage);

        try {
            deleteCommand.execute(invalidInput);
            String actualOutput = outputStream.toString();
            assertEquals(expectedMessage.trim(), actualOutput.trim(), "The printed error message for invalid input should match.");
            verifyNoInteractions(taskStorage);
        } finally {
            System.setOut(originalOutput);
        }
    }

    @Test
    public void execute_normalInput_sucess() {
        String validInput = "2";
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        toDoList.add(new ToDoTask("test"));
        toDoList.add(new ToDoTask("test2"));
        TaskStorage taskStorage = mock(TaskStorage.class);
        DeleteCommand deleteCommand = new DeleteCommand(toDoList, taskStorage);
        deleteCommand.execute(validInput);
        assertEquals(1, toDoList.size(), "The size of toDoList should be 1 after successful deletion");
    }

    @Test
    public void execute_nonNumberInputt_errorMessagePrinted() {
        String invalidInput = "not a number";
        String expectedMessage = "Pray, employ the noble digits as thy guiding input!\n(Please use numerics as input only)\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));
        ToDoList toDoList = new ToDoList(new ArrayList<>());
        TaskStorage taskStorage = mock(TaskStorage.class);
        DeleteCommand deleteCommand = new DeleteCommand(toDoList, taskStorage);

        try {
            deleteCommand.execute(invalidInput);
            String actualOutput = outputStream.toString();
            assertEquals(expectedMessage.trim(), actualOutput.trim(), "The printed error message for invalid input should match.");
            verifyNoInteractions(taskStorage);
        } finally {
            System.setOut(originalOutput);
        }
    }
}
