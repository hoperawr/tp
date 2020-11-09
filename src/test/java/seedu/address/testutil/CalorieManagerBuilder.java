package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;

public class CalorieManagerBuilder {

    private CalorieManager calorieManager;

    /**
     * Creates a {@code CalorieManagerBuilder}.
     */
    public CalorieManagerBuilder() {
        calorieManager = new CalorieManager();
    }

    /**
     * Adds an {@code Input} to the {@code CalorieManager} that we are building.
     */
    public CalorieManagerBuilder addInput(Input input) {
        try {
            calorieManager.addCalorieInput(input);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Adds an {@code Output} to the {@code CalorieManager} that we are building.
     */
    public CalorieManagerBuilder addOutput(Output output) {
        try {
            calorieManager.addCalorieOutput(output);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
        return this;
    }

    public CalorieManager build() {
        return calorieManager;
    }
}
