package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;

/**
 * Represents a profile in My Fitness Buddy records.
 */
public class Profile {

    // identity fields
    public final Name name;
    public final ID id;

    // data fields
    public final Height height;
    private Weight startingWeight;
    private final int age = 20;

    private Date startingDate;

    /**
     * Creates a profile.
     */
    public Profile(Name name, ID id, Height height, Weight startingWeight) {
        requireAllNonNull(name, id, height, startingWeight);
        this.name = name;
        this.id = id;
        this.height = height;
        this.startingWeight = startingWeight;
    }

    /**
     * Creates a profile with the option to indicate starting date for storage.
     */
    public Profile(Name name, ID id, Height height, Weight startingWeight, Date date) {
        requireAllNonNull(name, id, height, startingWeight);
        this.name = name;
        this.id = id;
        this.height = height;
        this.startingWeight = startingWeight;
        this.startingDate = date;
    }

    public Name getName() {
        assert name != null;
        return name;
    }

    public ID getId() {
        return id;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getStartingWeight() {
        return startingWeight;
    }

    public int getAge() {
        return this.age;
    }

    public Date getStartDate() {
        return startingDate;
    }

    /**
     * Sets the profile information of current data to {@code profile}.
     */
    public void setStartingDay(Date date) {
        assert date != null;
        this.startingDate = date;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Profile)) {
            return false;
        }
        Profile otherProfile = (Profile) other;
        return otherProfile.getId().equals(getId())
                && otherProfile.getName().equals(getName())
                && otherProfile.getHeight().equals(getHeight())
                && otherProfile.getStartingWeight().equals(getStartingWeight());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getName())
                .append(" ID: ")
                .append(getId())
                .append(" Height: ")
                .append(getHeight())
                .append(" Target Weight: ")
                .append(getStartingWeight());
        return builder.toString();
    }

}
