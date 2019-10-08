package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a new entry recording the user's blood sugar level.
 */
public class BloodSugarCommand extends Command {

    public static final String COMMAND_WORD = "bloodsugar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the bloodsugar level at the current datetime"
        + "Parameters: VALUE (must be a positive integer) "
        + "v/ [BLOODSUGARLEVEL]\n"
        + "Example: " + COMMAND_WORD
        + "v/ 78.9.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Bloodsugar command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
