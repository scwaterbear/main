package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.BloodSugar;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_BLOODSUGAR = "85355255";

    private Name name;
    private BloodSugar bloodSugar;
    private Set<Tag> tags;

    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        bloodSugar = new BloodSugar(DEFAULT_BLOODSUGAR);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        bloodSugar = personToCopy.getBloodSugar();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code BloodSugar} of the {@code Person} that we are building.
     */
    public PersonBuilder withBloodSugar(String bloodSugar) {
        this.bloodSugar = new BloodSugar(bloodSugar);
        return this;
    }

    public Person build() {
        return new Person(name, bloodSugar, tags);
    }

}
