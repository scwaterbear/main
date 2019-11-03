package seedu.sugarmummy.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.sugarmummy.commons.util.AppUtil.checkArgument;

/**
 * Represents a Bmi Record's height in the record book. Guarantees: immutable; is valid as declared in {@link
 * #isValidHeight(String)}
 */
public class Height {
    private static final double HEIGHT_CONSTRAINT = 3;
    public static final String MESSAGE_CONSTRAINTS =
            "Height should only contain positive real number "
                + "and must be less than " + HEIGHT_CONSTRAINT + " after rounding to 2 d.p.";
    public static final String VALIDATION_REGEX = "^+?\\d*\\.{0,1}\\d+$";
    private final double height;

    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        this.height = roundDouble(height);
    }

    public static boolean isValidHeight(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        return roundDouble(test) < HEIGHT_CONSTRAINT;
    }

    private static double roundDouble(String test) {
        double doubleToRound = Double.parseDouble(test);
        return Math.round(doubleToRound * 100.0) / 100.0;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && height == ((Height) other).height); // state check
    }

    @Override
    public int hashCode() {
        return ((Double) height).hashCode();
    }

    @Override
    public String toString() {
        return Double.toString(height);
    }
}
