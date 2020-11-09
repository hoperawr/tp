package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.RemoveCommand.MESSAGE_DELETE_CALORIE_SUCCESS;
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
import seedu.address.model.calorie.Calorie;
import seedu.address.model.person.Profile;
import seedu.address.testutil.TypicalDays;


public class RemoveCommandTest {
    private static final Profile validProfile = TypicalDays.DEFAULT_PROFILE;


    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, null, (LocalDate) null));
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, null, (Index) null));
    }

    @Test
    public void execute_dayIndexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Index indexD = Index.fromOneBased(20);
        Index indexC = Index.fromZeroBased(20);
        RemoveCommand removeCommand = new RemoveCommand(indexC, false, indexD);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX, () ->
                removeCommand.execute(model));
    }

    @Test
    public void execute_noAvailableDay_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        LocalDate date = LocalDate.parse("2018-12-25");
        Index indexC = Index.fromZeroBased(1);
        RemoveCommand removeCommand = new RemoveCommand(indexC, false, date);
        assertThrows(CommandException.class, RemoveCommand.NO_AVAILABLE_DAY, () ->
                removeCommand.execute(model));
    }

    @Test
    public void execute_calorieIndexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        LocalDate date = LocalDate.parse("2019-12-25");
        Index indexC = Index.fromZeroBased(2000);
        RemoveCommand removeCommand = new RemoveCommand(indexC, false, date);
        RemoveCommand removeCommand1 = new RemoveCommand(indexC, true, date);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_CALORIE_DISPLAYED_INDEX, () ->
                removeCommand.execute(model));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_CALORIE_DISPLAYED_INDEX, () ->
                removeCommand1.execute(model));
    }

    @Test
    public void execute_calorieCommandIndex_successfully() throws CommandException, IllegalValueException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        LocalDate date = LocalDate.parse("2019-12-25");
        Index indexC = Index.fromZeroBased(1);
        RemoveCommand removeCommand = new RemoveCommand(indexC, true, date);
        CommandResult commandResult = removeCommand.execute(model);
        Calorie remove = model
                .getDay(date)
                .getCalorieManager()
                .getCalorie(true, indexC);
        System.out.println(remove);
        assertEquals(String.format(MESSAGE_DELETE_CALORIE_SUCCESS, remove),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_calorieCommandDate_successfully() throws CommandException, IllegalValueException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Index indexD = Index.fromOneBased(1);
        Index indexC = Index.fromZeroBased(1);
        RemoveCommand removeCommand = new RemoveCommand(indexC, false, indexD);
        CommandResult commandResult = removeCommand.execute(model);
        Calorie remove = model.getMyFitnessBuddy()
                .getDayList()
                .get(indexD.getZeroBased())
                .getCalorieManager()
                .getCalorie(false, indexC);
        System.out.println(remove);
        assertEquals(String.format(MESSAGE_DELETE_CALORIE_SUCCESS, remove),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {

        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);

        RemoveCommand testA = new RemoveCommand(index1, true, index1);
        RemoveCommand testB = new RemoveCommand(index1, false, index2);

        assertEquals(testA.equals(testA), true);
        assertEquals(testA.equals(testB), false);
    }
}

