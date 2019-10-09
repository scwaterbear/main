package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditRecordDescriptor;
import seedu.address.model.record.BloodSugar;
import seedu.address.model.record.Name;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditRecordDescriptor objects.
 */
public class EditRecordDescriptorBuilder {

    private EditRecordDescriptor descriptor;

    public EditRecordDescriptorBuilder() {
        descriptor = new EditRecordDescriptor();
    }

    public EditRecordDescriptorBuilder(EditRecordDescriptor descriptor) {
        this.descriptor = new EditRecordDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecordDescriptor} with fields containing {@code record}'s details
     */
    public EditRecordDescriptorBuilder(Record record) {
        descriptor = new EditRecordDescriptor();
        descriptor.setName(record.getName());
        descriptor.setBloodSugar(record.getBloodSugar());
        descriptor.setTags(record.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code BloodSugar} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withBloodSugar(String bloodSugar) {
        descriptor.setBloodSugar(new BloodSugar(bloodSugar));
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRecordDescriptor}
     * that we are building.
     */
    public EditRecordDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditRecordDescriptor build() {
        return descriptor;
    }
}
