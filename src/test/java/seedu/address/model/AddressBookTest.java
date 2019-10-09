package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.ALICE;
import static seedu.address.testutil.TypicalRecords.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.record.Record;
import seedu.address.model.record.exceptions.DuplicateRecordException;
import seedu.address.testutil.RecordBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getRecordList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateRecords_throwsDuplicateRecordException() {
        // Two records with the same identity fields
        Record editedAlice = new RecordBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Record> newRecords = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newRecords);

        assertThrows(DuplicateRecordException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasRecord_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRecord(null));
    }

    @Test
    public void hasRecord_recordNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasRecord(ALICE));
    }

    @Test
    public void hasRecord_recordInAddressBook_returnsTrue() {
        addressBook.addRecord(ALICE);
        assertTrue(addressBook.hasRecord(ALICE));
    }

    @Test
    public void hasRecord_recordWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addRecord(ALICE);
        Record editedAlice = new RecordBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasRecord(editedAlice));
    }

    @Test
    public void getRecordList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRecordList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose records list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Record> records = FXCollections.observableArrayList();

        AddressBookStub(Collection<Record> records) {
            this.records.setAll(records);
        }

        @Override
        public ObservableList<Record> getRecordList() {
            return records;
        }
    }

}
