package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class BloodSugar {
    public final String value;

    public BloodSugar(String bloodSugar) {
        requireNonNull(bloodSugar);
        value = bloodSugar;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof BloodSugar // instanceof handles nulls
            && value.equals(((BloodSugar) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}