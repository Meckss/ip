package Commands;

/**
 * An interface defining a command that can be executed with a given input.
 * Implementing classes must provide the logic for the execute method.
 */
public interface Command {

    /**
     * Executes the command with the specified input.
     *
     * @param input the input object to be processed by the command
     */
    void execute(Object input);
}
