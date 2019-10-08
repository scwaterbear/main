package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BloodSugarTest {

    @Test
    public void equals() {
        BloodSugar bloodSugar = new BloodSugar("12.3");

        // same object -> returns true
        assertTrue(bloodSugar.equals(bloodSugar));

        // same values -> returns true
        BloodSugar remarkCopy = new BloodSugar(bloodSugar.value);
        assertTrue(bloodSugar.equals(remarkCopy));

        // different types -> returns false
        assertFalse(bloodSugar.equals(1));

        // null -> returns false
        assertFalse(bloodSugar.equals(null));

        // different remark -> returns false
        BloodSugar differentBloodSugar = new BloodSugar("23.4");
        assertFalse(bloodSugar.equals(differentBloodSugar));
    }
}
