package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.record.BloodSugar;
import seedu.address.model.record.Name;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Record[] getSampleRecords() {
        return new Record[] {
            new Record(new Name("Alex Yeoh"), new BloodSugar("87438807"),

                getTagSet("friends")),
            new Record(new Name("Bernice Yu"), new BloodSugar("99272758"),

                getTagSet("colleagues", "friends")),
            new Record(new Name("Charlotte Oliveiro"), new BloodSugar("93210283"),

                getTagSet("neighbours")),
            new Record(new Name("David Li"), new BloodSugar("91031282"),

                getTagSet("family")),
            new Record(new Name("Irfan Ibrahim"), new BloodSugar("92492021"),

                getTagSet("classmates")),
            new Record(new Name("Roy Balakrishnan"), new BloodSugar("92624417"),

                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Record sampleRecord : getSampleRecords()) {
            sampleAb.addRecord(sampleRecord);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
