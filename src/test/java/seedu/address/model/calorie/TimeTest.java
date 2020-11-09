package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null Time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid Time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("123")); // less than 4 digits
        assertFalse(Time.isValidTime("12345")); // more than 5 digits
        assertFalse(Time.isValidTime("9930")); // hour greater than 23
        assertFalse(Time.isValidTime("2399")); // min greater than 59
        assertFalse(Time.isValidTime("2460")); // both time and min out of range
        assertFalse(Time.isValidTime("23ab")); // contains alphabet
        assertFalse(Time.isValidTime("23$%")); // contains special character
        assertFalse(Time.isValidTime("ab$%")); // no digit
        assertFalse(Time.isValidTime("12 30")); // contains space
        assertFalse(Time.isValidTime("12 0")); // contains space

        // valid Time
        assertTrue(Time.isValidTime("2359")); // alphabets only
        assertTrue(Time.isValidTime("0000")); // alphabets only
        assertTrue(Time.isValidTime("1230")); // alphabets only

    }

    @Test
    public void isEquals() {
        Time time = new Time("0200");
        assertEquals(time, time);
        assertEquals(new Time("1130"), new Time("1130"));
        assertNotEquals(new Time("1130"), new Time("2359"));
        assertNotEquals(new Time("1130"), new Object());
    }
}
