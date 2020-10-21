package seedu.address.model.day;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.day.calorie.CalorieManager;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Day {

    // Identity fields
    private final Date date;
    private final Weight weight;
    private final CalorieManager calorieManager;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Class constructor
     */
    public Day(Date date, Weight weight, Set<Tag> tags) {
        requireAllNonNull(date, weight, tags);
        this.date = date;
        this.weight = weight;
        this.tags.addAll(tags);
        this.calorieManager = new CalorieManager();
    }

    /**
     * Class constructor
     */
    public Day(Date date, Weight weight, Set<Tag> tags, CalorieManager calorieManager) {
        requireAllNonNull(date, weight, tags, calorieManager);
        this.date = date;
        this.weight = weight;
        this.tags.addAll(tags);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        }

        return otherDay != null
                && otherDay.getDate().equals(getDate());
    }

    /**
     * Returns true if both days have the same identity and data fields.
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
        return otherDay.getDate().equals(getDate())
                && otherDay.getWeight().equals(getWeight())
                && otherDay.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, weight, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append(" Weight: ")
                .append(getWeight());
        //        .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}