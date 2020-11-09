package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;

/**
 * A utility class to help with building Day objects.
 */
public class DayBuilder {


    public static final String DEFAULT_DATE = "2020-01-02";
    public static final String DEFAULT_WEIGHT = "42";

    private Date date;
    private Weight weight;
    private CalorieManager calorieManager;

    /**
     * Creates a {@code DayBuilder} with the default details.
     */
    public DayBuilder() {
        date = new Date(DEFAULT_DATE);
        weight = new Weight(DEFAULT_WEIGHT);
        calorieManager = new CalorieManager();
    }

    /**
     * Initializes the DayBuilder with the data of {@code dayToCopy}.
     */
    public DayBuilder(Day dayToCopy) {
        date = dayToCopy.getDate();
        weight = dayToCopy.getWeight();
        calorieManager = dayToCopy.getCalorieManager();
        try {
            calorieManager.addCalorieOutput(new CalorieBuilder().buildOutput());
            calorieManager.addCalorieInput(new CalorieBuilder().buildInput());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the {@code Date} of the {@code Day} that we are building.
     */
    public DayBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Day} that we are building.
     */
    public DayBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }


    public Day build() {
        return new Day(date, weight, calorieManager);
    }

}
