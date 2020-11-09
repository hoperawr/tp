package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCalories.INPUT_A;
import static seedu.address.testutil.TypicalCalories.INPUT_B;

import org.junit.jupiter.api.Test;

class InputTest {

    private final String invalidInput = "";
    private final Food food = new Food("Laksa");
    private final Time time = new Time("1200");
    private final CalorieCount calorieCount = new CalorieCount("400");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Input(null, null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Input(new Time(invalidInput), food, calorieCount));
    }

    @Test
    public void constructor_invalidFood_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Input(time, new Food(invalidInput), calorieCount));
    }

    @Test
    public void constructor_invalidCalorieCount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Input(time, food, new CalorieCount(invalidInput)));
    }

    @Test
    void getFood() {
        Food validFood = new Food("Laksa");
        Input input = new Input(new Time("1200"), validFood, new CalorieCount("200"));
        assertEquals(validFood, input.getFood());
    }

    @Test
    void isSameInput() {
        assertTrue(INPUT_A.isSameInput(INPUT_A));
        assertFalse(INPUT_A.isSameInput(INPUT_B));
    }

    @Test
    public void toStringTest() {
        assertEquals(" Food: laksa Time: 1130 Calorie Intake: 100", INPUT_A.toString());
        assertNotEquals(INPUT_A.toString(), INPUT_B);
    }
}
