package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODSUGAR;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.BloodSugarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BloodSugar;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class BloodSugarCommandParser implements Parser<BloodSugarCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BloodSugarCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BLOODSUGAR);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BloodSugarCommand.MESSAGE_USAGE), ive);
        }

        String bloodSugar = argMultimap.getValue(PREFIX_BLOODSUGAR).orElse("");

        return new BloodSugarCommand(index, new BloodSugar(bloodSugar));
    }
}