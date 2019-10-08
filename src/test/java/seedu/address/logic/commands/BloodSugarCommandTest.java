package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.person.BloodSugar;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BloodSugarCommand.
 */
public class BloodSugarCommandTest {

    private static final String BLOODSUGAR_STUB = "99.9";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withBloodSugar(BLOODSUGAR_STUB).build();

        BloodSugarCommand bloodSugarCommand = new BloodSugarCommand(INDEX_FIRST_PERSON, new BloodSugar(editedPerson.getBloodSugar().value));

        String expectedMessage = String.format(BloodSugarCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(bloodSugarCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withBloodSugar("").build();

        BloodSugarCommand bloodSugarCommand = new BloodSugarCommand(INDEX_FIRST_PERSON,
            new BloodSugar(editedPerson.getBloodSugar().toString()));

        String expectedMessage = String.format(BloodSugarCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(bloodSugarCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
            .withBloodSugar(BLOODSUGAR_STUB).build();

        BloodSugarCommand bloodSugarCommand = new BloodSugarCommand(INDEX_FIRST_PERSON, new BloodSugar(editedPerson.getBloodSugar().value));

        String expectedMessage = String.format(BloodSugarCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(bloodSugarCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BloodSugarCommand bloodSugarCommand = new BloodSugarCommand(outOfBoundIndex, new BloodSugar(VALID_BLOODSUGAR_BOB));

        assertCommandFailure(bloodSugarCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        BloodSugarCommand bloodSugarCommand = new BloodSugarCommand(outOfBoundIndex, new BloodSugar(VALID_BLOODSUGAR_BOB));

        assertCommandFailure(bloodSugarCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
