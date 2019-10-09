package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRecord.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.BloodSugar;
import seedu.address.model.record.Name;

public class JsonAdaptedRecordTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_BLOODSUGAR = "+651234";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_BLOODSUGAR = BENSON.getBloodSugar().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRecordDetails_returnsRecord() throws Exception {
        JsonAdaptedRecord record = new JsonAdaptedRecord(BENSON);
        assertEquals(BENSON, record.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecord record =
                new JsonAdaptedRecord(INVALID_NAME, VALID_BLOODSUGAR, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(null, VALID_BLOODSUGAR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_invalidBloodSugar_throwsIllegalValueException() {
        JsonAdaptedRecord record =
                new JsonAdaptedRecord(VALID_NAME, INVALID_BLOODSUGAR, VALID_TAGS);
        String expectedMessage = BloodSugar.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_nullBloodSugar_throwsIllegalValueException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodSugar.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRecord record =
                new JsonAdaptedRecord(VALID_NAME, VALID_BLOODSUGAR, invalidTags);
        assertThrows(IllegalValueException.class, record::toModelType);
    }

}
