package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExerciseTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Exercise(null));
    }

    @Test
    public void constructor_invalidExercise_throwsIllegalArgumentException() {
        String invalidExercise = "";
        assertThrows(IllegalArgumentException.class, () -> new Exercise(invalidExercise));
    }

    @Test
    public void isValidExercise() {
        // invalid Exercise
        assertFalse(Exercise.isValidExercise("")); // empty string
        assertFalse(Exercise.isValidExercise(" ")); // spaces only

        // valid Exercise
        assertTrue(Exercise.isValidExercise("running")); // alphabets only
        assertTrue(Exercise.isValidExercise("running@stadium")); // contains special character
        assertTrue(Exercise.isValidExercise("run for 5km")); // contains number and space
        assertTrue(Exercise.isValidExercise("run for 5km @ track")); // contains number, alphabet and special char
    }

    @Test
    public void isEquals() {
        assertEquals(new Exercise("running"), new Exercise("running"));
        assertNotEquals(new Exercise("running"), new Exercise("swimming"));
        assertNotEquals(new Exercise("running"), new Object());
    }

}
