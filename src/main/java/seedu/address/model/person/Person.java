package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.calculator.Bmi;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.UniqueDayList;
import seedu.address.model.day.Weight;

/**
 * Represents a Person in My Fitness Buddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // if a person has DEFAULT_PROFILE, it means that the profile has not been set by user so no
    // daily records can be added.

    // Identity fields
    private static Profile defaultProfile =
            new Profile(new Name("DEFAULT"), new ID(), new Height(), new Weight());
    private Profile profile;
    private final UniqueDayList days;
    private final int age = 20;
    //    private double currentBmi = -1;
    private Date startingDate;

    /**
     * Every field must be present and not null.
     */
    public Person(Profile profile, UniqueDayList days) {
        requireAllNonNull(profile, days);
        this.profile = profile;
        this.days = days;
    }

    /**
     * Profile cannot be null and be present.
     */
    public Person() {
        this.profile = defaultProfile;
        this.days = new UniqueDayList();
    }

    /**
     * Profile cannot be null and be present.
     */
    public Person(Profile profile) {
        requireNonNull(profile);
        this.profile = profile;
        this.days = new UniqueDayList();
    }

    /**
     * Returns true if the profile is not changed from default.
     */
    public boolean isDefaultProfile() {
        assert profile != null;
        return profile.equals(defaultProfile);
    }

    /**
     * Returns the profile of a Person instance.
     */
    public Profile getProfile() {
        assert profile != null;
        return profile;
    }

    /**
     * Returns an unmodifiable day list.
     */
    public ObservableList<Day> getDayList() {
        return days.asUnmodifiableObservableList();
    }

    /**
     * Returns the UniqueDayList that can be modified.
     */
    public UniqueDayList getDays() {
        return days;
    }

    /**
     * Replaces the contents of the day list with {@code days}.
     * {@code persons} must not contain duplicate days.
     */
    public void setDays(List<Day> days) {
        assert days != null;
        this.days.setDays(days);
    }

    /**
     * Sets the profile information of current data to {@code profile}.
     */
    public void setStartingDay(Date date) {
        assert date != null;
        profile.setStartingDay(date);
    }

    /**
     * Sets the profile information of current data to {@code profile}.
     */
    public void setProfile(Profile profile) {
        assert profile != null;
        this.profile = profile;
    }

    /**
     * Returns true if a day with the same identity as {@code day} exists in the my fitness buddy records.
     */
    public boolean hasDay(Day day) {
        requireNonNull(day);
        return days.contains(day);
    }

    /**
     * Returns true if a day in the my fitness buddy records with the same date as the current date.
     */
    public boolean hasDay(LocalDate date) {
        return days.contains(date);
    }

    /**
     * Gets a day in the my fitness buddy records with a specific date.
     */
    public Day getDay(LocalDate date) {
        return days.getDate(date);
    }

    /**
     * Gets starting day
     */
    public Date getDay() {
        return profile.getStartDate();
    }

    /**
     * Adds a day to the my fitness buddy records.
     * The day must not already exist in the my fitness buddy records.
     */
    public void addDay(Day day) {
        assert day != null;
        day.setAge(this.age);
        day.setHeight(profile.height);
        day.setStartingWeight(profile.getStartingWeight());
        days.add(day);
    }

    /**
     * Replaces the given day {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the my fitness buddy records.
     * The day identity of {@code editedPerson} must not be the same as another
     * existing day in the my fitness buddy records.
     */
    public void setDay(Day target, Day editedDay) {
        requireNonNull(editedDay);

        days.setDay(target, editedDay);
    }

    /**
     * Removes {@code key} from this {@code MyFitnessBuddy}.
     * {@code key} must exist in the records.
     */
    public void removeDay(Day key) {
        days.remove(key);
    }

    /**
     * Returns true if both persons have the same ID.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.equals(this); // check is same id and name
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getProfile().getId().equals(getProfile().getId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(profile, days);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Profile: ")
                .append(getProfile().toString())
                .append(" Days: ")
                .append(getDayList());
        return builder.toString();
    }

    /**
     * Returns true if the date of this day is after otherDay
     */
    public boolean isAfter(Person otherPerson) {
        int thisPerson = Integer.parseInt(this.profile.id.value);
        int otherPer = Integer.parseInt(otherPerson.profile.id.value);
        return thisPerson > otherPer;
    }

    /**
     * Returns current bmi
     */
    public double getCurrentBmi() {
        List<Day> list = days.asUnmodifiableObservableList();
        int size = list.size();
        if (size == 0) {
            return Bmi.calculateBmi(profile.height, profile.getStartingWeight());
        } else {
            Day currentDay = list.get(size - 1);
            Weight currentWeight = currentDay.getWeight();
            Height currentHeight = profile.height;
            return Bmi.calculateBmi(currentHeight, currentWeight);
        }
    }

    /**
     * returns current bmi progress
     */
    public double getProgress() {
        double currentBmi = getCurrentBmi();
        double startBmi = Bmi.calculateBmi(profile.getHeight(), profile.getStartingWeight());
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
