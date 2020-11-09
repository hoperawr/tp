package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;

/**
 * A utility class containing a list of {@code Day} objects to be used in tests.
 */
public class TypicalDays {

    public static final Day DAY1 = new DayBuilder().withDate("2020-11-23").withWeight("45").build();
    public static final Day DAY2 = new DayBuilder().withDate("2020-05-12").withWeight("80").build();
    public static final Day DAY3 = new DayBuilder().withDate("2020-01-23").withWeight("75").build();
    public static final Day DAY4 = new DayBuilder().withDate("2020-01-13").withWeight("66").build();
    public static final Day DAY5 = new DayBuilder().withDate("2020-09-12").withWeight("42").build();
    public static final Day DAY6 = new DayBuilder().withDate("2020-03-01").withWeight("51").build();
    public static final Day DAY7 = new DayBuilder().withDate("2019-12-25").withWeight("73").build();

    // Manually added
    public static final Day HOON = new DayBuilder().withDate("2020-06-09").withWeight("56")
           .build();
    public static final Day IDA = new DayBuilder().withDate("2020-10-11").withWeight("40")
          .build();

    // Manually added - Day's details found in {@code CommandTestUtil}
    // MDAY stands for manually added day
    public static final Day MDAY1 = new DayBuilder().withDate(VALID_DATE_1).withWeight(VALID_WEIGHT_1)
         .build();
    public static final Day MDAY2 = new DayBuilder().withDate(VALID_DATE_2).withWeight(VALID_WEIGHT_2)
            .build();
    public static final Profile DEFAULT_PROFILE = TypicalProfiles.DEFAULT_PROFILE;

    private TypicalDays() {} // prevents instantiation

    /**
     * Returns an {@code MyFitnessBuddy} with all the typical days.
     */
    public static MyFitnessBuddy getTypicalMyFitnessBuddy() {
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy();
        myFitnessBuddy.setPerson(new Person(DEFAULT_PROFILE)); // sets to default profile
        for (Day day : getTypicalDays()) {
            myFitnessBuddy.addDay(day);
        }
        return myFitnessBuddy;
    }

    /**
     * Returns an {@code getAnotherFitnessBuddy} with all the typical days and calories in them.
     */
    public static MyFitnessBuddy getAnotherMyFitnessBuddy() {
        MyFitnessBuddy ab = new MyFitnessBuddy();
        ab.setPerson(new Person(DEFAULT_PROFILE));
        for (Day day : getTypicalDays()) {
            ab.addDay(day);
        }
        try {
            for (Day d : ab.getDayList()) {
                d.getCalorieManager().addCalorieInput(TypicalCalories.INPUT_A);
                d.getCalorieManager().addCalorieInput(TypicalCalories.INPUT_B);
                d.getCalorieManager().addCalorieInput(TypicalCalories.INPUT_C);
                d.getCalorieManager().addCalorieOutput(TypicalCalories.OUTPUT_A);
                d.getCalorieManager().addCalorieOutput(TypicalCalories.OUTPUT_B);
                d.getCalorieManager().addCalorieOutput(TypicalCalories.OUTPUT_C);
            }
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        return ab;
    }

    public static List<Day> getTypicalDays() {
        return new ArrayList<>(Arrays.asList(DAY1, DAY2, DAY3, DAY4, DAY5, DAY6, DAY7));
    }

    public static List<Day> getDuplicateDays() {
        return new ArrayList<>(Arrays.asList(DAY1, DAY1, DAY1, DAY2, DAY3));
    }
}
