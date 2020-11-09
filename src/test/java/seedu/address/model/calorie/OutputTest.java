package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCalories.OUTPUT_A;
import static seedu.address.testutil.TypicalCalories.OUTPUT_B;

import org.junit.jupiter.api.Test;

class OutputTest {

    private final String invalidInput = "";
    private final Exercise exercise = new Exercise("Running");
    private final Time time = new Time("1200");
    private final CalorieCount calorieCount = new CalorieCount("400");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Output(null, null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Output(new Time(invalidInput), exercise, calorieCount));
    }

    @Test
    public void constructor_invalidFood_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Output(time, new Exercise(invalidInput), calorieCount));
    }

    @Test
    public void constructor_invalidCalorieCount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Output(time, exercise, new CalorieCount(invalidInput)));
    }

    @Test
    public void getFood() {
        Exercise validExercise = new Exercise("running");
        Output output = new Output(new Time("1200"), validExercise, new CalorieCount("200"));
        assertEquals(validExercise, output.getExercise());
    }

    @Test
    public void isSameOutput() {
        assertTrue(OUTPUT_A.isSameOutput(OUTPUT_A));
        assertFalse(OUTPUT_A.isSameOutput(OUTPUT_B));
    }

    @Test
    public void toStringTest() {
        assertEquals(" Exercise: running Time: 1130 Calorie Burnt: 111", OUTPUT_A.toString());
        assertNotEquals(OUTPUT_A.toString(), OUTPUT_B);
    }
}
