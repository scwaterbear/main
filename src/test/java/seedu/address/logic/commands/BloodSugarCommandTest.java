package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BloodSugarCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.BloodSugar;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BloodSugarCommand.
 */
public class BloodSugarCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final BloodSugar bloodSugar = new BloodSugar("99.9");

        assertCommandFailure(new BloodSugarCommand(INDEX_FIRST_PERSON, bloodSugar), model,
            String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), bloodSugar));
    }

    @Test
    public void equals() {
        final BloodSugarCommand standardCommand = new BloodSugarCommand(INDEX_FIRST_PERSON,
            new BloodSugar(VALID_BLOODSUGAR_AMY));

        // same values -> returns true
        BloodSugarCommand commandWithSameValues = new BloodSugarCommand(INDEX_FIRST_PERSON,
            new BloodSugar(VALID_BLOODSUGAR_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new BloodSugarCommand(INDEX_SECOND_PERSON,
            new BloodSugar(VALID_BLOODSUGAR_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new BloodSugarCommand(INDEX_FIRST_PERSON,
            new BloodSugar(VALID_BLOODSUGAR_BOB))));
    }
}
