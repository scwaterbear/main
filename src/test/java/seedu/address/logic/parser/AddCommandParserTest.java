package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BLOODSUGAR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLOODSUGAR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOODSUGAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRecords.AMY;
import static seedu.address.testutil.TypicalRecords.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.record.BloodSugar;
import seedu.address.model.record.Name;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.RecordBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Record expectedRecord = new RecordBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + BLOODSUGAR_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedRecord));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + BLOODSUGAR_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedRecord));

        // multiple bloodSugar - last bloodSugar accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOODSUGAR_DESC_AMY + BLOODSUGAR_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedRecord));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOODSUGAR_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedRecord));

        // multiple tags - all accepted
        Record expectedRecordMultipleTags = new RecordBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + BLOODSUGAR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedRecordMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Record expectedRecord = new RecordBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + BLOODSUGAR_DESC_AMY,
                new AddCommand(expectedRecord));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + BLOODSUGAR_DESC_BOB,
                expectedMessage);

        // missing bloodsugar prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_BLOODSUGAR_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_BLOODSUGAR_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + BLOODSUGAR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid bloodsugar
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_BLOODSUGAR_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, BloodSugar.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + BLOODSUGAR_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + BLOODSUGAR_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + BLOODSUGAR_DESC_BOB
                 + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
