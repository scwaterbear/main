package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.ALICE;
import static seedu.address.testutil.TypicalRecords.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.record.exceptions.DuplicateRecordException;
import seedu.address.model.record.exceptions.RecordNotFoundException;
import seedu.address.testutil.RecordBuilder;

public class UniqueRecordListTest {

    private final UniqueRecordList uniqueRecordList = new UniqueRecordList();

    @Test
    public void contains_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.contains(null));
    }

    @Test
    public void contains_recordNotInList_returnsFalse() {
        assertFalse(uniqueRecordList.contains(ALICE));
    }

    @Test
    public void contains_recordInList_returnsTrue() {
        uniqueRecordList.add(ALICE);
        assertTrue(uniqueRecordList.contains(ALICE));
    }

    @Test
    public void contains_recordWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRecordList.add(ALICE);
        Record editedAlice = new RecordBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueRecordList.contains(editedAlice));
    }

    @Test
    public void add_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.add(null));
    }

    @Test
    public void add_duplicateRecord_throwsDuplicateRecordException() {
        uniqueRecordList.add(ALICE);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.add(ALICE));
    }

    @Test
    public void setRecord_nullTargetRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(null, ALICE));
    }

    @Test
    public void setRecord_nullEditedRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(ALICE, null));
    }

    @Test
    public void setRecord_targetRecordNotInList_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> uniqueRecordList.setRecord(ALICE, ALICE));
    }

    @Test
    public void setRecord_editedRecordIsSameRecord_success() {
        uniqueRecordList.add(ALICE);
        uniqueRecordList.setRecord(ALICE, ALICE);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(ALICE);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasSameIdentity_success() {
        uniqueRecordList.add(ALICE);
        Record editedAlice = new RecordBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueRecordList.setRecord(ALICE, editedAlice);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(editedAlice);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasDifferentIdentity_success() {
        uniqueRecordList.add(ALICE);
        uniqueRecordList.setRecord(ALICE, BOB);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(BOB);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasNonUniqueIdentity_throwsDuplicateRecordException() {
        uniqueRecordList.add(ALICE);
        uniqueRecordList.add(BOB);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.setRecord(ALICE, BOB));
    }

    @Test
    public void remove_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.remove(null));
    }

    @Test
    public void remove_recordDoesNotExist_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> uniqueRecordList.remove(ALICE));
    }

    @Test
    public void remove_existingRecord_removesRecord() {
        uniqueRecordList.add(ALICE);
        uniqueRecordList.remove(ALICE);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_nullUniqueRecordList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecords((UniqueRecordList) null));
    }

    @Test
    public void setRecords_uniqueRecordList_replacesOwnListWithProvidedUniqueRecordList() {
        uniqueRecordList.add(ALICE);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(BOB);
        uniqueRecordList.setRecords(expectedUniqueRecordList);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecords((List<Record>) null));
    }

    @Test
    public void setRecords_list_replacesOwnListWithProvidedList() {
        uniqueRecordList.add(ALICE);
        List<Record> recordList = Collections.singletonList(BOB);
        uniqueRecordList.setRecords(recordList);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(BOB);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_listWithDuplicateRecords_throwsDuplicateRecordException() {
        List<Record> listWithDuplicateRecords = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.setRecords(listWithDuplicateRecords));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRecordList.asUnmodifiableObservableList().remove(0));
    }
}
