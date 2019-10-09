package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.BloodSugar;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new BloodSugar("87438807"),

                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new BloodSugar("99272758"),

                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new BloodSugar("93210283"),

                getTagSet("neighbours")),
            new Person(new Name("David Li"), new BloodSugar("91031282"),

                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new BloodSugar("92492021"),

                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new BloodSugar("92624417"),

                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
