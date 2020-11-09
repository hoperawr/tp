package seedu.address.model.calorie;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Class to store the relevant information for any calorie output
 */
public class Output extends Calorie {

    private Exercise exercise;
    /**
     * Every field must be present and not null.
     */
    public Output(Time time, Exercise exercise, CalorieCount calorieCount) {
        super(calorieCount, time);
        requireAllNonNull(exercise, time, calorieCount);
        this.exercise = exercise;
    }

    public Exercise getExercise() {
        return exercise;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Exercise: ")
                .append(getExercise())
                .append(" Time: ")
                .append(getTime())
                .append(" Calorie Burnt: ")
                .append(getCalorieCount());

        return builder.toString();
    }

    /**
     * Returns true if a otherOutput has the same attributes as this Output
     * @param otherOutput that is being checked with
     */
    public boolean isSameOutput(Output otherOutput) {
        return otherOutput.getExercise().exercise.equals(this.exercise.toString())
                && otherOutput.getTime().time.equals(this.time.toString())
                && otherOutput.getCalorieCount().calorieCount.equals(this.calorieCount.toString());
    }
}
