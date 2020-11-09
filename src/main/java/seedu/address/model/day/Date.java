package seedu.address.model.day;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's weight in the record.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {


    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the form of YYYY-MM-DD.";

    public static final String VALIDATION_REGEX = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";

    public final String value;

    private final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isCorrectRegrex(date), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(date), getErrorMessage(date));
        this.value = date;
        this.date = LocalDate.parse(date);
    }

    /**
     * checks if test matches validation regrex
     * @param test to be checked
     */
    public static boolean isCorrectRegrex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid date number.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
    /**
     * Returns error message
     * @param date
     */
    private String getErrorMessage(String date) {
        String result = "";
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            result = e.getMessage();
        }
        return result;
    }



    /**
     * Returns true if a date is after the other date
     */
    public boolean dateAfter(Date otherDate) {
        return this.get().isAfter(otherDate.get());
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns the LocalDate stored in the Date class
     */
    public LocalDate get() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date) // instanceof handles nulls
                && date.equals(((Date) other).get()); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
