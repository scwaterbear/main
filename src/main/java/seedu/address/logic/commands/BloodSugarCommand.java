package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Adds a new entry recording the user's blood sugar level.
 */
public class BloodSugarCommand extends Command {

    public static final String COMMAND_WORD = "bloodsugar";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from blood sugar");
    }
}
