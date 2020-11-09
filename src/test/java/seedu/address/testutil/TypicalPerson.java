package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;

public class TypicalPerson {

    public static final Person PERSON1 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE1)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON2 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE2)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON3 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE3)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON4 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE4)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON5 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE5)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON6 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE6)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON7 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE7)
            .withDays(TypicalDays.getTypicalDays()).build();
    public static final Person PERSON8 = new PersonBuilder().withProfile(TypicalProfiles.PROFILE8)
            .withDays(TypicalDays.getTypicalDays()).build();

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(PERSON1, PERSON2, PERSON3, PERSON4, PERSON5, PERSON6));
    }

    /**
     * Returns an {@code MyFitnessBuddy} with all the typical persons.
     */
    public static MyFitnessBuddy getTypicalMyFitnessBuddy() {
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy();
        for (Person person : getTypicalPersons()) {
            myFitnessBuddy.addPerson(person);
        }
        return myFitnessBuddy;
    }

    /**
     * Returns an {@code getAnotherFitnessBuddy} with all the typical days and calories in them.
     */
    public static MyFitnessBuddy getAnotherMyFitnessBuddy() {
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy();
        for (Person person : getTypicalPersons()) {
            myFitnessBuddy.addPerson(person);
        }
        myFitnessBuddy.getPersonList().stream().forEach(x->x.getDayList().stream()
                .forEach(y-> {
                    try {
                        y.getCalorieManager().addCalorieInput(TypicalCalories.INPUT_A);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }));
        myFitnessBuddy.getPersonList().stream().forEach(x->x.getDayList().stream()
                .forEach(y-> {
                    try {
                        y.getCalorieManager().addCalorieInput(TypicalCalories.INPUT_B);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }));
        myFitnessBuddy.getPersonList().stream().forEach(x->x.getDayList().stream()
                .forEach(y-> {
                    try {
                        y.getCalorieManager().addCalorieInput(TypicalCalories.INPUT_C);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }));
        myFitnessBuddy.getPersonList().stream().forEach(x->x.getDayList().stream()
                .forEach(y-> {
                    try {
                        y.getCalorieManager().addCalorieOutput(TypicalCalories.OUTPUT_A);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }));
        myFitnessBuddy.getPersonList().stream().forEach(x->x.getDayList().stream()
                .forEach(y-> {
                    try {
                        y.getCalorieManager().addCalorieOutput(TypicalCalories.OUTPUT_B);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }));
        myFitnessBuddy.getPersonList().stream().forEach(x->x.getDayList().stream()
                .forEach(y-> {
                    try {
                        y.getCalorieManager().addCalorieOutput(TypicalCalories.OUTPUT_C);
                    } catch (IllegalValueException e) {
                        e.printStackTrace();
                    }
                }));
        return myFitnessBuddy;
    }
    public static MyFitnessBuddy getSimpleMyFitnessBuddy() {
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy();
        Person person = new Person(
                new Profile(new Name("test"), new ID("1111"), new Height("180"), new Weight("100")));
        person.setStartingDay(new Date("2020-10-10"));
        Day day = new Day(new Date("2020-10-10"), new Weight("100"));
        day.setStartingWeight(person.getProfile().getStartingWeight());
        day.setAge(person.getProfile().getAge());
        day.setHeight(person.getProfile().getHeight());
        person.addDay(day);
        myFitnessBuddy.addPerson(person);
        return myFitnessBuddy;
    }

    public static MyFitnessBuddy getSimpleMyFitnessBuddy2() {
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy();
        Person person = new Person(
                new Profile(new Name("test"), new ID("1111"), new Height("180"), new Weight("100")));
        person.setStartingDay(new Date("2020-10-10"));
        Day day = new Day(new Date("2020-10-10"), new Weight("100"));
        day.setStartingWeight(person.getProfile().getStartingWeight());
        day.setAge(person.getProfile().getAge());
        day.setHeight(person.getProfile().getHeight());
        person.addDay(day);
        Day day2 = new Day(new Date("2020-10-11"), new Weight("123"));
        day2.setStartingWeight(person.getProfile().getStartingWeight());
        day2.setAge(person.getProfile().getAge());
        day2.setHeight(person.getProfile().getHeight());
        person.addDay(day2);
        myFitnessBuddy.addPerson(person);
        return myFitnessBuddy;
    }
}
