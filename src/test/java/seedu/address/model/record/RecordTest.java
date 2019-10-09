package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.ALICE;
import static seedu.address.testutil.TypicalRecords.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecordBuilder;

public class RecordTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Record record = new RecordBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> record.getTags().remove(0));
    }

    @Test
    public void isSameRecord() {
        // same object -> returns true
        assertTrue(ALICE.isSameRecord(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameRecord(null));

        // different blood sugar -> returns false
        Record editedAlice = new RecordBuilder(ALICE).withBloodSugar(VALID_BLOODSUGAR_BOB).build();
        assertFalse(ALICE.isSameRecord(editedAlice));

        // different name -> returns false
        editedAlice = new RecordBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameRecord(editedAlice));

        // same name, same bloodsugar, different attributes -> returns true
        editedAlice = new RecordBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRecord(editedAlice));

        //uncomment test when there are more than 2 data fields. Now there is name and blood sugar only.
        // same name, different attributes -> returns true
        //editedAlice = new RecordBuilder(ALICE).withBloodSugar(VALID_BLOODSUGAR_BOB)
        //.withTags(VALID_TAG_HUSBAND).build();
        //assertTrue(ALICE.isSameRecord(editedAlice));

        // same name, same blood sugar, different attributes -> returns true
        editedAlice = new RecordBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameRecord(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Record aliceCopy = new RecordBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different record -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Record editedAlice = new RecordBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different blood sugar -> returns false
        editedAlice = new RecordBuilder(ALICE).withBloodSugar(VALID_BLOODSUGAR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new RecordBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
