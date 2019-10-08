package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODSUGAR;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.BloodSugar;

/**
 * Adds a new entry recording the user's blood sugar level.
 */
public class BloodSugarCommand extends Command {

    public static final String COMMAND_WORD = "bloodsugar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the bloodsugar level"
        + "Existing bloodsugar will be overwritten by the input.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_BLOODSUGAR + "[BLOODSUGAR]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_BLOODSUGAR + "89.0.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Bloodsugar: %2$d";

    private final Index index;
    private final BloodSugar bloodSugar;

    /**
     * @param index of the data entry
     * @param bloodSugar at that moment
     */
    public BloodSugarCommand(Index index, BloodSugar bloodSugar) {
        requireAllNonNull(index, bloodSugar);

        this.index = index;
        this.bloodSugar = bloodSugar;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), bloodSugar));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BloodSugarCommand)) {
            return false;
        }

        // state check
        BloodSugarCommand e = (BloodSugarCommand) other;
        return index.equals(e.index)
            && bloodSugar.equals(e.bloodSugar);
    }
}
