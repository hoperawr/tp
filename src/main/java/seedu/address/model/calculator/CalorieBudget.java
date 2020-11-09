package seedu.address.model.calculator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;



/**
 * class that contains methods to Calorie related calculation
 */
public class CalorieBudget {

    private static final double PAL = 1.75;
    /**
     * @param height of the person
     * @param weight of the person
     * @param age of the person
     * @return Basal Metabolic Rate in KCal
     */
    public static int calculateBasalMetabolic(Height height, Weight weight, int age) {
        requireAllNonNull(height, weight, age);
        double h = Integer.parseInt(height.value);
        double w = Integer.parseInt(weight.value);
        double bmr = (10 * w) + (6.25 * h) - (5 * age) + 5;
        return (int) bmr;
    }

    /**
     *
     * @param totalCalorieIn to be used to calculate calorie budget
     * @param totalCalorieOut to be used to calculate calorie budget
     * @param basalMetabolic to be used to calculate calorie budget
     * @return calorie surplus that user can afford to consume
     */
    public static int calculateCalorieSurplus(int totalCalorieIn, int totalCalorieOut, int basalMetabolic) {
        requireAllNonNull(totalCalorieIn, totalCalorieOut, basalMetabolic);
        double adjustedBasal = PAL * basalMetabolic;
        return (int) (totalCalorieOut + adjustedBasal - totalCalorieIn);
    }
}
