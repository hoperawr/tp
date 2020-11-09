package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_EDIT_CALORIE_SUCCESS;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NOT_EDITED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPerson.getAnotherMyFitnessBuddy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;
import seedu.address.model.person.Profile;
import seedu.address.testutil.TypicalDays;

public class ChangeCalorieCommandTest {
    private static final Profile validProfile = TypicalDays.DEFAULT_PROFILE;


    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new ChangeCommand((LocalDate) null, null, null));
        assertThrows(NullPointerException.class, () ->
                new ChangeCommand((Index) null, null, null));
    }

    @Test
    public void execute_dayIndexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        Index indexD = Index.fromOneBased(20);
        Index indexC = Index.fromZeroBased(20);
        ChangeCommand changeCommand = new ChangeCommand(indexD, ccd, indexC);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX, () ->
                changeCommand.execute(model));
    }

    @Test
    public void execute_noAvailableDay_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        LocalDate date = LocalDate.parse("2018-10-10");
        Index indexC = Index.fromZeroBased(20);
        ChangeCommand changeCommand = new ChangeCommand(date, ccd, indexC);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX, () ->
                changeCommand.execute(model));
    }

    @Test
    public void execute_calorieIndexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setIsOut(true);
        ccd.setTime(new Time("2359"));
        LocalDate date = LocalDate.parse("2019-12-25");
        Index indexC = Index.fromZeroBased(2000);
        ChangeCommand changeCommand = new ChangeCommand(date, ccd, indexC);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_CALORIE_DISPLAYED_INDEX, () ->
                changeCommand.execute(model));
    }

    @Test
    public void execute_notEditedOut_throwsCommandException() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setIsOut(true);
        ccd.setTime(new Time("1130"));
        Index indexD = Index.fromOneBased(1);
        Index indexC = Index.fromOneBased(1);
        ChangeCommand changeCommand = new ChangeCommand(indexD, ccd, indexC);
        assertThrows(CommandException.class, MESSAGE_NOT_EDITED, () ->
                changeCommand.execute(model));
    }

    @Test
    public void execute_notEditedIn_throwsCommandException() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setIsOut(false);
        ccd.setTime(new Time("1130"));
        LocalDate date = LocalDate.parse("2019-12-25");
        Index indexC = Index.fromOneBased(1);
        ChangeCommand changeCommand = new ChangeCommand(date, ccd, indexC);
        assertThrows(CommandException.class, MESSAGE_NOT_EDITED, () ->
                changeCommand.execute(model));
    }

    @Test
    public void execute_calorieCommandIndexOutput_successfully() throws CommandException, IllegalValueException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setIsOut(true);
        ccd.setTime(new Time("2359"));
        Index indexD = Index.fromOneBased(1);
        Index indexC = Index.fromOneBased(1);
        Output result = new Output(new Time("2359"), new Exercise("running"), new CalorieCount("111"));
        ChangeCommand changeCommand = new ChangeCommand(indexD, ccd, indexC);
        CommandResult commandResult = changeCommand.execute(model);
        assertEquals(String.format(MESSAGE_EDIT_CALORIE_SUCCESS, result),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_calorieCommandIndexInput_successfully() throws CommandException, IllegalValueException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setIsOut(false);
        ccd.setTime(new Time("2359"));
        LocalDate date = LocalDate.parse("2019-12-25");
        Index indexC = Index.fromOneBased(1);
        Input result = new Input(new Time("2359"), new Food("laksa"), new CalorieCount("100"));
        ChangeCommand changeCommand = new ChangeCommand(date, ccd, indexC);
        CommandResult commandResult = changeCommand.execute(model);
        assertEquals(String.format(MESSAGE_EDIT_CALORIE_SUCCESS, result),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        ChangeCommand.ChangeCalorieDescriptor ccd = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setTime(new Time("1230"));
        ChangeCommand.ChangeCalorieDescriptor ccd2 = new ChangeCommand.ChangeCalorieDescriptor();
        ccd.setTime(new Time("1130"));

        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);

        ChangeCommand testA = new ChangeCommand(index1, ccd, index1);
        ChangeCommand testB = new ChangeCommand(index1, ccd2, index2);
        ChangeCommand testC = new ChangeCommand(index1, ccd2, index1);

        assertEquals(testA.equals(testA), true);
        assertEquals(testA.equals(testB), false);
        assertEquals(testB.equals(testC), false);
    }
}
