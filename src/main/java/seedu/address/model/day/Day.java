package seedu.address.model.day;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.calculator.Bmi;
import seedu.address.model.calculator.CalorieBudget;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.person.Height;

/**
 * Represents a Day in the Person in MyFitnessBuddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Day {

    // Identity fields
    private final Date date;
    private final Weight weight;
    private final CalorieManager calorieManager;
    private int age = 0;
    private Height height = null;
    private Weight startingWeight = null;
    private int surplus = getBalance();

    /**
     * Class constructor
     */
    public Day(Date date, Weight weight) {
        requireAllNonNull(date, weight);
        this.date = date;
        this.weight = weight;
        this.calorieManager = new CalorieManager();
    }

    /**
     * Class constructor
     */
    public Day(Date date, Weight weight, CalorieManager calorieManager) {
        requireAllNonNull(date, weight, calorieManager);
        this.date = date;
        this.weight = weight;
        this.calorieManager = calorieManager;
    }

    public Date getDate() {
        return date;
    }

    public Weight getWeight() {
        return weight;
    }

    public CalorieManager getCalorieManager() {
        return calorieManager;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getStartingWeight() {
        return startingWeight;
    }

    public int getAge() {
        return age;
    }

    /**
     * Returns true if both days are of the same date and not the weight and tag field.
     */
    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        }

        return otherDay != null
                && otherDay.getDate().equals(getDate());
    }

    /**
     * Returns true if the date of this day is after otherDay
     */
    public boolean isAfter(Day otherDay) {
        return this.getDate().dateAfter(otherDay.getDate());
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStartingWeight(Weight weight) {
        this.startingWeight = weight;
    }

    public void setHeight(Height height) {
        this.height = height;
    }
    /**
     * Returns true if both days have the same date and data fields.
     * This defines a stronger notion of equality between two days.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Day)) {
            return false;
        }

        Day otherDay = (Day) other;
        return otherDay.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, weight);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append(" Weight: ")
                .append(getWeight());
        return builder.toString();
    }

    /**
     * Returns the amount of calorie budget for a particular day
     */
    public int getBalance() {
        if (height == null || weight == null) {
            return -1;
        } else {
            int bmr = CalorieBudget.calculateBasalMetabolic(height, weight, age);
            int calorieIn = calorieManager.getTotalInputCalorie();
            int calorieOut = getCalorieManager().getTotalOutputCalorie();
            return CalorieBudget.calculateCalorieSurplus(calorieIn, calorieOut, bmr);
        }
    }

    /**
     * is the the person currently losing weight calorie budget > 0
     */
    public boolean isLosing() {
        return getBalance() >= 0;
    }

    /**
     * returns the progress of the person towards the idea bmi
     */
    public double getProgress() {
        double currentBmi = Bmi.calculateBmi(height, weight);
        double startBmi = Bmi.calculateBmi(height, startingWeight);
        if (startBmi < 22.5) {
            return 1;
        }
        double endBmi = 22.5;
        double totalBmiToChange = startBmi - endBmi;
        double differenceWithEnd = currentBmi - endBmi;
        double percentageChange = 1 - (differenceWithEnd / totalBmiToChange);

        if (percentageChange > 1) {
            percentageChange = 1;
        }

        if (percentageChange < 0) {
            percentageChange = 0;
        }
        return percentageChange;
    }

}
