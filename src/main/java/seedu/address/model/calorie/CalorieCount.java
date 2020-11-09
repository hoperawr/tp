package seedu.address.model.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Input's/Output's calorie count in the record
 */
public class CalorieCount {

    public static final String MESSAGE_CONSTRAINTS =
            "Calorie Count should be a non zero positive integer equal or less than 2147483647,\n"
                    + " and it should not be blank";

    public static final String VALIDATION_REGEX = "[1-9][\\p{Digit}]*";

    public final String calorieCount;

    /**
     * Constructs a {@code CalorieCount}.
     *
     * @param calorieCount A valid calorieCount.
     */
    public CalorieCount(String calorieCount) {
        requireNonNull(calorieCount);
        checkArgument(isValidCalorieCount(calorieCount), MESSAGE_CONSTRAINTS);
        this.calorieCount = calorieCount;
    }

    /**
     * Returns true if the test is a valid calorieCount input
     * @param test string input to be tested
     */
    public static boolean isValidCalorieCount(String test) {
        requireNonNull(test);
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }

        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return calorieCount;
    }

    /**
     * Returns true if both Calories have the same calorie string.
     * This defines a stronger notion of equality between two calories.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CalorieCount)) {
            return false;
        }

        CalorieCount otherCalorieCount = (CalorieCount) other;
        return otherCalorieCount.calorieCount.equals(this.calorieCount);
    }
}

