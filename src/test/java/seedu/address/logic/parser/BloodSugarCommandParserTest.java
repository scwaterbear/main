package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODSUGAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BloodSugarCommand;
import seedu.address.model.person.BloodSugar;

public class BloodSugarCommandParserTest {
    private BloodSugarCommandParser parser = new BloodSugarCommandParser();
    private final String nonEmptyBloodSugar = "99.9";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_BLOODSUGAR + nonEmptyBloodSugar;
        BloodSugarCommand expectedCommand = new BloodSugarCommand(INDEX_FIRST_PERSON,
            new BloodSugar(nonEmptyBloodSugar));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_BLOODSUGAR;
        expectedCommand = new BloodSugarCommand(INDEX_FIRST_PERSON, new BloodSugar(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, BloodSugarCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, BloodSugarCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, BloodSugarCommand.COMMAND_WORD + " " + nonEmptyBloodSugar, expectedMessage);
    }
}