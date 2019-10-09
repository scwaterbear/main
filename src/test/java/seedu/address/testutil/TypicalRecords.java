package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODSUGAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.record.Record;

/**
 * A utility class containing a list of {@code Record} objects to be used in tests.
 */
public class TypicalRecords {

    public static final Record ALICE = new RecordBuilder().withName("Alice Pauline")
            .withBloodSugar("94351253")
            .withTags("friends").build();
    public static final Record BENSON = new RecordBuilder().withName("Benson Meier")
            .withBloodSugar("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Record CARL = new RecordBuilder().withName("Carl Kurz").withBloodSugar("95352563")
            .build();
    public static final Record DANIEL = new RecordBuilder().withName("Daniel Meier").withBloodSugar("87652533")
            .withTags("friends").build();
    public static final Record ELLE = new RecordBuilder().withName("Elle Meyer").withBloodSugar("9482224")
            .build();
    public static final Record FIONA = new RecordBuilder().withName("Fiona Kunz").withBloodSugar("9482427")
            .build();
    public static final Record GEORGE = new RecordBuilder().withName("George Best").withBloodSugar("9482442")
            .build();

    // Manually added
    public static final Record HOON = new RecordBuilder().withName("Hoon Meier").withBloodSugar("8482424")
            .build();
    public static final Record IDA = new RecordBuilder().withName("Ida Mueller").withBloodSugar("8482131")
            .build();

    // Manually added - Record's details found in {@code CommandTestUtil}
    public static final Record AMY = new RecordBuilder().withName(VALID_NAME_AMY).withBloodSugar(VALID_BLOODSUGAR_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Record BOB = new RecordBuilder().withName(VALID_NAME_BOB).withBloodSugar(VALID_BLOODSUGAR_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRecords() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical records.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Record record : getTypicalRecords()) {
            ab.addRecord(record);
        }
        return ab;
    }

    public static List<Record> getTypicalRecords() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
