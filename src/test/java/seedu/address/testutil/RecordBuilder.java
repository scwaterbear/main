package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.record.BloodSugar;
import seedu.address.model.record.Name;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Record objects.
 */
public class RecordBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_BLOODSUGAR = "85355255";

    private Name name;
    private BloodSugar bloodSugar;
    private Set<Tag> tags;

    public RecordBuilder() {
        name = new Name(DEFAULT_NAME);
        bloodSugar = new BloodSugar(DEFAULT_BLOODSUGAR);
        tags = new HashSet<>();
    }

    /**
     * Initializes the RecordBuilder with the data of {@code recordToCopy}.
     */
    public RecordBuilder(Record recordToCopy) {
        name = recordToCopy.getName();
        bloodSugar = recordToCopy.getBloodSugar();
        tags = new HashSet<>(recordToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Record} that we are building.
     */
    public RecordBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Record} that we are building.
     */
    public RecordBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code BloodSugar} of the {@code Record} that we are building.
     */
    public RecordBuilder withBloodSugar(String bloodSugar) {
        this.bloodSugar = new BloodSugar(bloodSugar);
        return this;
    }

    public Record build() {
        return new Record(name, bloodSugar, tags);
    }

}
