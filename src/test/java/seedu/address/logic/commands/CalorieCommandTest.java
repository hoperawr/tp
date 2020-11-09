package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCalories.INPUT_A;
import static seedu.address.testutil.TypicalCalories.INPUT_B;
import static seedu.address.testutil.TypicalPerson.getAnotherMyFitnessBuddy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;
import seedu.address.model.person.Profile;
import seedu.address.testutil.TypicalDays;


public class CalorieCommandTest {
    private static final Profile validProfile = TypicalDays.DEFAULT_PROFILE;


    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalorieCommand(null, null, null));
    }


    @Test
    public void execute_noAvailableDay_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        String date = "2020-12-31";
        CalorieCommand calorieCommand = new CalorieCommand(INPUT_A, false, date);
        assertThrows(CommandException.class, CalorieCommand.NO_AVAILABLE_DAY, () -> calorieCommand.execute(model));
    }

    @Test
    public void execute_outOfRange_throwsCommandException() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Calorie input = new Input(new Time("1230"), new Food("food"), new CalorieCount("2147483646"));
        Calorie output = new Output(new Time("1230"), new Exercise("exercise"), new CalorieCount("2147483646"));
        CalorieCommand calorieCommand = new CalorieCommand(input, false, "2020-09-12");

        CalorieCommand finalCalorieCommand = calorieCommand;
        assertThrows(CommandException.class, CalorieManager.INSANE_INPUT_CALORIE, () ->
                finalCalorieCommand.execute(model));
        calorieCommand = new CalorieCommand(output, true, "2020-09-12");
        CalorieCommand finalCalorieCommand1 = calorieCommand;
        assertThrows(CommandException.class, CalorieManager.INSANE_OUTPUT_CALORIE, () ->
                finalCalorieCommand1.execute(model));
    }

    @Test
    public void execute_calorieCommand_successfully() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Calorie input = new Input(new Time("1230"), new Food("food"), new CalorieCount("214"));
        Calorie output = new Output(new Time("1230"), new Exercise("exercise"), new CalorieCount("214"));
        CalorieCommand calorieCommand = new CalorieCommand(input, false, "2020-09-12");

        CommandResult commandResult = calorieCommand.execute(model);
        assertEquals(String.format(CalorieCommand.MESSAGE_SUCCESS, input), commandResult.getFeedbackToUser());

        calorieCommand = new CalorieCommand(output, true, "2020-09-12");
        CommandResult commandResult1 = calorieCommand.execute(model);
        assertEquals(String.format(CalorieCommand.MESSAGE_SUCCESS, output), commandResult1.getFeedbackToUser());
    }

    @Test
    public void getDate() {
        String date = "2020-12-31";
        String invalidDate = "2020-13-31";
        CalorieCommand calorieCommand = new CalorieCommand(INPUT_A, false, date);

        assertThrows(CommandException.class, CalorieCommand.INVALID_DATE , ()-> calorieCommand.getDate(invalidDate));

        String empty = "";
        try {
            assertEquals(LocalDate.now() , calorieCommand.getDate(empty));
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(LocalDate.parse(date) , calorieCommand.getDate(date));
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void equals() {

        CalorieCommand testA = new CalorieCommand(INPUT_A, true, "2020-10-10");
        CalorieCommand testB = new CalorieCommand(INPUT_B, true, "2020-10-10");

        assertEquals(testA.equals(testA), true);
        assertEquals(testA.equals(testB), false);
    }
}

