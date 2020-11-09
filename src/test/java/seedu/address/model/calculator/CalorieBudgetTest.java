package seedu.address.model.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;

public class CalorieBudgetTest {

    @Test
    public void addCalculateBasalMetabolic() {
        Integer age = null;
        assertThrows(NullPointerException.class, () ->
                CalorieBudget.calculateBasalMetabolic(null, null, age));
        assertEquals(2030,
                CalorieBudget.calculateBasalMetabolic(new Height("180"), new Weight("100"), 20));
        assertEquals(2167,
                CalorieBudget.calculateBasalMetabolic(new Height("170"), new Weight("120"), 20));
        assertEquals(2305,
                CalorieBudget.calculateBasalMetabolic(new Height("160"), new Weight("140"), 20));
    }

    @Test
    public void calculateCalorieBudget() {
        Integer age = null;
        assertThrows(NullPointerException.class, () ->
                CalorieBudget.calculateCalorieSurplus(age, age, age));
        assertEquals(3552,
                CalorieBudget.calculateCalorieSurplus(0, 0, 2030));
        assertEquals(3792,
                CalorieBudget.calculateCalorieSurplus(0, 0, 2167));
        assertEquals(3892,
                CalorieBudget.calculateCalorieSurplus(0, 100, 2167));
        assertEquals(4033,
                CalorieBudget.calculateCalorieSurplus(0, 0, 2305));
        assertEquals(3933,
                CalorieBudget.calculateCalorieSurplus(100, 0, 2305));
    }
}
