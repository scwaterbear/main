package seedu.address.logic.parser.aesthetics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_BACKGROUND_COLOUR_NO_ARGS_REQUIREMENT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BACKGROUND_REPEAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BACKGROUND_SIZE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BG_REPEAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BG_SIZE;
import static seedu.address.logic.parser.ParserUtil.parseBackground;

import java.util.Optional;

import seedu.address.logic.commands.aesthetics.BackgroundCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.aesthetics.Background;

/**
 * Parses input arguments and creates a new FontColourCommand object
 */
public class BackgroundCommandParser implements Parser<BackgroundCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand and returns an EditCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public BackgroundCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BackgroundCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BG_SIZE, PREFIX_BG_REPEAT);

        String backgroundArg = argMultimap.getPreamble();

        Background background = parseBackground(backgroundArg);

        if (background.isBackgroundColour() && !argMultimap.isEmpty()) {
            throw new ParseException(MESSAGE_BACKGROUND_COLOUR_NO_ARGS_REQUIREMENT);
        }

        Optional<String> bgSize;
        Optional<String> bgRepeat;

        if ((bgSize = argMultimap.getValue(PREFIX_BG_SIZE)).isPresent()) { 
            if (!Background.isValidBackgroundSize(bgSize.get())) {
                throw new ParseException(MESSAGE_INVALID_BACKGROUND_SIZE);
            }
            bgSize = bgSize.get().equals("") ? Optional.of("auto") : bgSize;
        }

        if ((bgRepeat = argMultimap.getValue(PREFIX_BG_REPEAT)).isPresent()) {
            if (!Background.isValidBackgroundRepeat(bgRepeat.get())) {
                throw new ParseException(MESSAGE_INVALID_BACKGROUND_REPEAT);
            }
            bgRepeat = bgRepeat.get().equals("") ? Optional.of("repeat") : bgRepeat;
        }

        String bgSizeToString = bgSize.orElse("");
        String bgRepeatToString = bgRepeat.orElse("");

        background.setBgSize(bgSizeToString);
        background.setBgRepeat(bgRepeatToString);

        return new BackgroundCommand(background);
    }

}
