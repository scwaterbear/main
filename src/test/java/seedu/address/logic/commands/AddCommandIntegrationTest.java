package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecords.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.record.Record;
import seedu.address.testutil.RecordBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newRecord_success() {
        Record validRecord = new RecordBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addRecord(validRecord);

        assertCommandSuccess(new AddCommand(validRecord), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validRecord), expectedModel);
    }

    @Test
    public void execute_duplicateRecord_throwsCommandException() {
        Record recordInList = model.getAddressBook().getRecordList().get(0);
        assertCommandFailure(new AddCommand(recordInList), model, AddCommand.MESSAGE_DUPLICATE_RECORD);
    }

}
