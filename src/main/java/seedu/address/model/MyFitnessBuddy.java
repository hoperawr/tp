package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MyFitnessBuddy implements ReadOnlyMyFitnessBuddy {

    //private final UniqueDayList days;
    //private Profile profile;
    private Person person;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        //days = new UniqueDayList();
        person = new Person();
    }

    public MyFitnessBuddy() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public MyFitnessBuddy(ReadOnlyMyFitnessBuddy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the day list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setDays(List<Day> days) {
        getPerson().setDays(days);
    }

    public void setPerson(Person p) {
        this.person = p;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyMyFitnessBuddy newData) {
        requireNonNull(newData);
        setPerson(newData.getPerson());
        //setDays(newData.getDayList());
    }

    /**
     * Sets the profile information of current data to {@code profile}.
     */

    public void setProfile(Profile profile) {
        getPerson().setProfile(profile);
    }

    /**
     * Checks if the current data {@code Person } has a profile.
     */

    public boolean isDefaultProfile() {
        return getPerson().isDefaultProfile();
    }

    //// day-level operations

    /**
     * Returns true if a day with the same identity as {@code day} exists in the address book.
     */
    public boolean hasDay(Day day) {
        requireNonNull(day);
        return getPerson().getDays().contains(day);
    }

    /**
     * Returns true if a day in the address book with the same date as the current date.
     */
    public boolean hasDay(LocalDate date) {
        requireNonNull(date);
        return getDayList().contains(date);
    }

    /**
     * get a day in the address book with a specific date
     */
    public Day getDay(LocalDate date) {
        return getPerson().getDay(date);
    }
    /**
     * Adds a day to the address book.
     * The day must not already exist in the address book.
     */
    public void addDay(Day p) {
        getPerson().addDay(p);
    }

    /**
     * Replaces the given day {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The day identity of {@code editedPerson} must not be the same as another existing day in the address book.
     */
    public void setDay(Day target, Day editedDay) {
        requireNonNull(editedDay);

        getPerson().setDay(target, editedDay);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDay(Day key) {
        getPerson().getDays().remove(key);
    }

    //// util methods

    @Override
    public ObservableList<Day> getDayList() {
        return getPerson().getDayList();
    }

    @Override
    public Profile getProfile() {
        return getPerson().getProfile();
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MyFitnessBuddy // instanceof handles nulls
                && getDayList().equals(((MyFitnessBuddy) other).getDayList()));
    }

    @Override
    public int hashCode() {
        return getPerson().hashCode();
    }

    @Override
    public String toString() {
        return getPerson().getDayList().size() + " persons";
        // TODO: refine later
    }
}