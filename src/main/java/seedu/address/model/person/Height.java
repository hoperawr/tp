package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's heights in the profile.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS =
            "Height should be in between 50-272 unless you are the world's tallest or shortest person";
    public final String value;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height in centimetres.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        value = height;
    }

    /**
     * Constructs a {@code Height}.
     * for default profile
     */
    public Height() {
        value = "DEFAULT";
    }

    /**
     * Returns true if a given string is a valid height number.
     */
    public static boolean isValidHeight(String test) {
        requireNonNull(test);
        int testHeight;
        try {
            testHeight = Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return (50 < testHeight) && (testHeight < 272);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && value.equals(((Height) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
