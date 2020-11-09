package seedu.address.model.calorie;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CalorieCountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalorieCount(null));
    }

    @Test
    public void constructor_invalidCalorieCount_throwsIllegalArgumentException() {
        String invalidCalorieCount = "";
        assertThrows(IllegalArgumentException.class, () -> new CalorieCount(invalidCalorieCount));
    }

    @Test
    public void isEquals() {
        CalorieCount test1 = new CalorieCount("12345");
        CalorieCount test2 = new CalorieCount("54321");
        assertNotEquals(test1, test2);
        assertNotEquals(test1, new Object());
        assertEquals(test1, test1);
    }



    @Test
    public void isValidCalorieCount() {
        // null CalorieCount
        assertThrows(NullPointerException.class, () -> CalorieCount.isValidCalorieCount(null));

        // invalid CalorieCount
        assertFalse(CalorieCount.isValidCalorieCount("")); // empty string
        assertFalse(CalorieCount.isValidCalorieCount(" ")); // spaces only
        assertFalse(CalorieCount.isValidCalorieCount("123a")); // contains alphabet
        assertFalse(CalorieCount.isValidCalorieCount("123$")); // contains special character
        assertFalse(CalorieCount.isValidCalorieCount("10.1")); // decimal point
        assertFalse(CalorieCount.isValidCalorieCount("-10.1")); // negative number
        assertFalse(CalorieCount.isValidCalorieCount("2147483648")); // max integer + 1
        assertFalse(CalorieCount.isValidCalorieCount("0")); // 0 as input


        // valid CalorieCount
        assertTrue(CalorieCount.isValidCalorieCount("123455")); // numbers only within range

    }
}
