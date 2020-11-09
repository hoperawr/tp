package seedu.address.model.calculator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;



/**
 * class that contains methods to calculate BMI
 */
public class Bmi {

    /**
     *
     * @param height
     * @param weight
     * @return bmi
     */
    public static double calculateBmi(Height height, Weight weight) {
        requireAllNonNull(height, weight);
        double h = Integer.parseInt(height.value);
        double w = Integer.parseInt(weight.value);
        double bmi = w / ((h / 100) * (h / 100));
        double roundBmi = Math.round(bmi * 100) / 100.0;
        return roundBmi;
    }
}
