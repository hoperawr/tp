package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCalories.INPUT_A;
import static seedu.address.testutil.TypicalCalories.INPUT_B;
import static seedu.address.testutil.TypicalCalories.OUTPUT_A;
import static seedu.address.testutil.TypicalCalories.OUTPUT_B;

import org.junit.jupiter.api.Test;


public class CalorieTest {

    @Test
    public void getCalorieCount() {
        assertEquals(new CalorieCount("100"), INPUT_A.getCalorieCount());
        assertEquals(new CalorieCount("111"), OUTPUT_A.getCalorieCount());
        assertNotEquals(new CalorieCount("100"), INPUT_B.getCalorieCount());
        assertNotEquals(new CalorieCount("111"), OUTPUT_B.getCalorieCount());
    }

    @Test
    public void getTime() {
        assertEquals(new Time("1130"), INPUT_A.getTime());
        assertEquals(new Time("1130"), OUTPUT_A.getTime());
        assertNotEquals(new Time("1130"), INPUT_B.getTime());
        assertNotEquals(new Time("1130"), OUTPUT_B.getTime());
    }

    @Test
    public void happensAfter() {
        assertTrue(INPUT_B.happenAfter(INPUT_A));
        assertTrue(OUTPUT_B.happenAfter(OUTPUT_A));
        assertFalse(INPUT_A.happenAfter(INPUT_B));
        assertFalse(OUTPUT_A.happenAfter(OUTPUT_B));
    }

    @Test
    public void equals() {
        assertNotEquals(INPUT_B, INPUT_A);
        assertEquals(INPUT_B, INPUT_B);
        assertNotEquals(OUTPUT_A, OUTPUT_B);
        assertEquals(INPUT_B, INPUT_B);
        assertNotEquals(INPUT_A, new Object());
    }

}
