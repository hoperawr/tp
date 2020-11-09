package seedu.address.model.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;


public class BmiTest {

    @Test
    public void calculateBmi() {
        assertThrows(NullPointerException.class, () -> Bmi.calculateBmi(null, null));
        assertEquals(30.86, Bmi.calculateBmi(new Height("180"), new Weight("100")));
        assertEquals(39.18, Bmi.calculateBmi(new Height("175"), new Weight("120")));
        assertEquals(23.44, Bmi.calculateBmi(new Height("160"), new Weight("60")));
        assertEquals(20.76, Bmi.calculateBmi(new Height("170"), new Weight("60")));
    }
}
