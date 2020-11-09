package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.day.Weight;

class ProfileTest {
    private Name name = new Name("jon");
    private ID id = new ID("1219");
    private Height height = new Height("170");
    private Weight startingWeight = new Weight("70");
    private Profile profile = new Profile(name, id, height, startingWeight);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Profile(null, null, null, null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    void getName() {
        assertTrue(name.equals(profile.getName()));
    }

    @Test
    void getId() {
        assertTrue(id.equals(profile.getId()));
    }

    @Test
    void getHeight() {
        assertTrue(height.equals(profile.getHeight()));
    }

    @Test
    void getStartingtWeight() {
        assertTrue(startingWeight.equals(profile.getStartingWeight()));
    }

    @Test
    void testEquals() {
        assertTrue(profile.equals(new Profile(name, id, height, startingWeight)));
        assertFalse(profile.equals(startingWeight));
    }
}
