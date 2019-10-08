package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a new entry recording the user's blood sugar level.
 */
public class BloodSugarCommand extends Command {

    public static final String COMMAND_WORD = "bloodsugar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the bloodsugar level"
        + "Existing bloodsugar will be overwritten by the input.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "b/ [BLOODSUGAR]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + "b/ 89.0.";


    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Bloodsugar command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
