package seedu.address.model.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.ReadOnlyMyFitnessBuddy;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.UniqueDayList;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;

/**
 * Contains utility methods for populating {@code MyFitnessBuddy} with sample data.
 */
public class SampleDataUtil {
    public static UniqueDayList getSampleDays() {
        ObservableList<Input> sampleInput = FXCollections.observableArrayList();
        sampleInput.add(new Input(new Time("1230"), new Food("Laksa"), new CalorieCount("800")));
        ObservableList<Output> sampleOutput = FXCollections.observableArrayList();
        sampleOutput.add(new Output(new Time("1800"), new Exercise("Run"), new CalorieCount("200")));
        CalorieManager sampleCalorieManager = new CalorieManager(sampleInput, sampleOutput);
        UniqueDayList sampleDays = new UniqueDayList();
        sampleDays.add(new Day(new Date("2020-10-11"), new Weight("80"), sampleCalorieManager));
        sampleDays.add(new Day(new Date("2020-10-12"), new Weight("82")));
        return sampleDays;
    }

    public static Person getSamplePerson() {
        Profile sampleProfile = new Profile(new Name("John"),
                new ID("2103"), new Height("177"), new Weight("80"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-09-30"));
        return samplePerson;
    }

    public static Person getSamplePerson2() {
        Profile sampleProfile = new Profile(new Name("Daniel"),
                new ID("2104"), new Height("170"), new Weight("75"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    public static Person getSamplePerson3() {
        Profile sampleProfile = new Profile(new Name("Tom"),
                new ID("2105"), new Height("180"), new Weight("75"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    public static Person getSamplePerson4() {
        Profile sampleProfile = new Profile(new Name("Adam"),
                new ID("2106"), new Height("176"), new Weight("79"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    public static Person getSamplePerson5() {
        Profile sampleProfile = new Profile(new Name("Harry"),
                new ID("2107"), new Height("178"), new Weight("71"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    public static Person getSamplePerson6() {
        Profile sampleProfile = new Profile(new Name("Charlie"),
                new ID("2108"), new Height("175"), new Weight("72"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    public static Person getSamplePerson7() {
        Profile sampleProfile = new Profile(new Name("William"),
                new ID("2109"), new Height("172"), new Weight("68"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    public static Person getSamplePerson8() {
        Profile sampleProfile = new Profile(new Name("Bob"),
                new ID("2110"), new Height("174"), new Weight("82"));
        UniqueDayList dayList = generateDayList(sampleProfile);
        Person samplePerson = new Person(sampleProfile, dayList);
        samplePerson.setStartingDay(new Date("2020-08-29"));
        return samplePerson;
    }

    /**
     * Generates a sample {@code UniqueDayList} for each sample profile.
     */
    public static UniqueDayList generateDayList(Profile sampleProfile) {
        UniqueDayList dayList = getSampleDays();
        for (Day day : dayList) {
            day.setAge(sampleProfile.getAge());
            day.setHeight(sampleProfile.getHeight());
            day.setStartingWeight(sampleProfile.getStartingWeight());
        }
        return dayList;
    }

    public static ReadOnlyMyFitnessBuddy getSampleMyFitnessBuddy() {
        MyFitnessBuddy sampleMyFitnessBuddy = new MyFitnessBuddy();
        sampleMyFitnessBuddy.addPerson(getSamplePerson());
        sampleMyFitnessBuddy.addPerson(getSamplePerson2());
        sampleMyFitnessBuddy.addPerson(getSamplePerson3());
        sampleMyFitnessBuddy.addPerson(getSamplePerson4());
        sampleMyFitnessBuddy.addPerson(getSamplePerson5());
        sampleMyFitnessBuddy.addPerson(getSamplePerson6());
        sampleMyFitnessBuddy.addPerson(getSamplePerson7());
        sampleMyFitnessBuddy.addPerson(getSamplePerson8());
        return sampleMyFitnessBuddy;
    }
}
