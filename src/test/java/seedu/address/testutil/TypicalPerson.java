package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.person.Person;

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
}
