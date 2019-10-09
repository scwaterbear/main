package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodSugarTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodSugar(null));
    }

    @Test
    public void constructor_invalidBloodSugar_throwsIllegalArgumentException() {
        String invalidBloodSugar = "";
        assertThrows(IllegalArgumentException.class, () -> new BloodSugar(invalidBloodSugar));
    }

    @Test
    public void isValidBloodSugar() {
        // null blood sugar
        assertThrows(NullPointerException.class, () -> BloodSugar.isValidBloodSugar(null));

        // invalid blood sugar
        assertFalse(BloodSugar.isValidBloodSugar("")); // empty string
        assertFalse(BloodSugar.isValidBloodSugar(" ")); // spaces only
        assertFalse(BloodSugar.isValidBloodSugar("91")); // less than 3 numbers
        assertFalse(BloodSugar.isValidBloodSugar("bloodsugar")); // non-numeric
        assertFalse(BloodSugar.isValidBloodSugar("9011p041")); // alphabets within digits
        assertFalse(BloodSugar.isValidBloodSugar("9312 1534")); // spaces within digits

        // valid blood sugar
        assertTrue(BloodSugar.isValidBloodSugar("911")); // exactly 3 numbers
        assertTrue(BloodSugar.isValidBloodSugar("93121534"));
        assertTrue(BloodSugar.isValidBloodSugar("124293842033123")); // long blood sugar number
    }
}
