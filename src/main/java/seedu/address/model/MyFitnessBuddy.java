package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the My Fitness Buddy level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MyFitnessBuddy implements ReadOnlyMyFitnessBuddy {

    private UniquePersonList persons;
    //private Profile profile;

    //serves as a container for all the ui to obtain information from
    private Person person;

    //serves as a pointer to which profile is being selected
    private Person currentPerson;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        person = new Person();
        currentPerson = new Person();
    }

    public MyFitnessBuddy() {
    }

    /**
     * Creates an MyFitnessBuddy using the data in the {@code toBeCopied}
     */
    public MyFitnessBuddy(ReadOnlyMyFitnessBuddy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the day list with {@code days}.
     * {@code days} must not contain duplicate days.
     */
    public void setDays(List<Day> days) {
        getPerson().setDays(days);
    }

    public void setPerson(Person p) {
        this.person = p;
    }

    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }


    /**
     * Resets the existing data of this {@code MyFitnessBuddy} with {@code newData}.
     */
    public void resetData(ReadOnlyMyFitnessBuddy newData) {
        requireNonNull(newData);
        setDays(newData.getDayList());
        setPersons(newData.getPersonList());
        setCurrentPerson(new Person());
    }

    /**
     * Resets the existing data of this {@code MyFitnessBuddy} with {@code newData}.
     */
    public void resetPersons() {
        persons = new UniquePersonList();
    }

    /**
     * Sets the profile information of current data to {@code profile}.
     */

    public void setProfile(Profile profile) {
        this.currentPerson.setProfile(profile);
        this.person.setProfile(profile);
    }

    /**
     * Checks if the current data {@code Person} has a profile.
     */

    public boolean isDefaultProfile() {
        return this.currentPerson.isDefaultProfile();
    }

    //// day-level operations

    /**
     * Returns true if a day with the same identity as {@code day} exists in My Fitness Buddy.
     */
    public boolean hasDay(Day day) {
        requireNonNull(day);
        return getPerson().hasDay(day);
    }

    /**
     * Returns true if a day in My Fitness Buddy with the same date as the current date.
     */
    public boolean hasDay(LocalDate date) {
        requireNonNull(date);
        return getPerson().hasDay(date);
    }

    /**
     * get a day in My Fitness Buddy with a specific date
     */
    public Day getDay(LocalDate date) {
        return getPerson().getDay(date);
    }

    /**
     * Adds a day to My Fitness Buddy.
     * The day must not already exist in My Fitness Buddy.
     */
    public void addDay(Day day) {
        currentPerson.addDay(day);
        day.setStartingWeight(person.getProfile().getStartingWeight());
        day.setHeight(person.getProfile().getHeight());
    }

    /**
     * Replaces the given day {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in My Fitness Buddy.
     * The day identity of {@code editedPerson} must not be the same as another existing day in My Fitness Buddy.
     */
    public void setDay(Day target, Day editedDay) {
        requireNonNull(editedDay);
        this.currentPerson.setDay(target, editedDay);
        getPerson().setDay(target, editedDay);
    }

    /**
     * Removes {@code key} from this {@code MyFitnessBuddy}.
     * {@code key} must exist in My Fitness Buddy.
     */
    public void removeDay(Day key) {
        this.currentPerson.getDays().remove(key);
        updateDay();
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

    public void setCurrentPerson(Person toSet) {
        this.currentPerson = toSet;
        this.person.setProfile(currentPerson.getProfile());
        this.person.setDays(currentPerson.getDayList());
    }

    /**
     * Adds a new person
     * @param toAdd is the person to be added
     */
    public void addPerson(Person toAdd) {
        this.currentPerson = toAdd;
        this.person.setProfile(currentPerson.getProfile());
        this.person.setDays(currentPerson.getDayList());
        this.persons.add(currentPerson);
    }

    public boolean hasPerson(Person toCheck) {
        return this.persons.contains(toCheck);
    }

    public void updateDay() {
        this.person.setDays(this.currentPerson.getDayList());
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return this.persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersons() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MyFitnessBuddy // instanceof handles nulls
                && getPersonList().equals(((MyFitnessBuddy) other).getPersonList()));
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
