package seedu.address.model.calorie;

import java.util.Objects;

/**
 * abstract class that input and output extends from
 */
public abstract class Calorie {
    public static final String MESSAGE_TYPE_CONSTRAINT = "type can only be in or out";

    // Identity fields
    protected CalorieCount calorieCount;
    protected Time time;
    /**
     * Every field must be present and not null.
     */
    public Calorie(CalorieCount calorieCount, Time time) {
        this.calorieCount = calorieCount;
        this.time = time;
    }
    public CalorieCount getCalorieCount() {
        return calorieCount;
    }

    public Time getTime() {
        return time;
    }

    /**
     * Returns true if the current output happens after the input output
     */
    public boolean happenAfter(Calorie calorie) {
        return this.getTime().isAfter(calorie.getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(time);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Calorie)) {
            return false;
        }
        Calorie otherCalorie = (Calorie) other;

        return otherCalorie.getTime().time.equals(getTime().time);
    }
}
