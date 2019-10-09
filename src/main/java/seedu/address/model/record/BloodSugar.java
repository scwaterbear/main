package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Record's blood sugar in the records.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodSugar(String)}
 */
public class BloodSugar {


    public static final String MESSAGE_CONSTRAINTS =
            "BloodSugar numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code BloodSugar}.
     *
     * @param bloodSugar A valid bloodSugar level.
     */
    public BloodSugar(String bloodSugar) {
        requireNonNull(bloodSugar);
        checkArgument(isValidBloodSugar(bloodSugar), MESSAGE_CONSTRAINTS);
        value = bloodSugar;
    }

    /**
     * Returns true if a given string is a valid blood sugar level.
     */
    public static boolean isValidBloodSugar(String test) {
        return test.matches(VALIDATION_REGEX);
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
