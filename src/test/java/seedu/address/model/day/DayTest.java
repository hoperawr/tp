package seedu.address.model.day;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_2;
import static seedu.address.testutil.TypicalDays.DAY1;
import static seedu.address.testutil.TypicalDays.MDAY2;

import org.junit.jupiter.api.Test;

import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;
import seedu.address.testutil.DayBuilder;

public class DayTest {

    public static final Time TIME = new Time("1200");
    public static final Exercise EXERCISE = new Exercise("run");
    public static final Food FOOD = new Food("Laksa");
    public static final CalorieCount CALORIE_COUNT = new CalorieCount("300");
    public static final Input INPUT = new Input(TIME, FOOD, CALORIE_COUNT);
    public static final Output OUTPUT = new Output(TIME, EXERCISE, CALORIE_COUNT);

    @Test
    public void isSameDay() {
        // same object -> returns true
        assertTrue(DAY1.isSameDay(DAY1));

        // null -> returns false
        assertFalse(DAY1.isSameDay(null));

        // same date, different weight -> returns true
        Day editedDay1 = new DayBuilder(DAY1).withWeight(VALID_WEIGHT_2).build();
        assertTrue(DAY1.isSameDay(editedDay1));

        // different date, same weight -> returns false
        editedDay1 = new DayBuilder(DAY1).withDate(VALID_DATE_2).build();
        assertFalse(DAY1.isSameDay(editedDay1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Day day1Copy = new DayBuilder(DAY1).build();
        assertTrue(DAY1.equals(day1Copy));

        // same object -> returns true
        assertTrue(DAY1.equals(DAY1));

        // null -> returns false
        assertFalse(DAY1.equals(null));

        // different type -> returns false
        assertFalse(DAY1.equals(5));

        // different day -> returns false
        assertFalse(DAY1.equals(MDAY2));

        // different date -> returns false
        Day editedDay1 = new DayBuilder(DAY1).withDate(VALID_DATE_2).build();
        assertFalse(DAY1.equals(editedDay1));
    }

}
