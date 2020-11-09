package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPerson.getAnotherMyFitnessBuddy;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class LoginCommandTest {
    @Test
    public void constructor_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LoginCommand(null));
    }

    @Test
    public void execute_loginIndexOutOfBounds_throwsCommandException() {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Index index = Index.fromOneBased(20);
        LoginCommand loginCommand = new LoginCommand(index);
        assertThrows(CommandException.class, "not valid index", () ->
                loginCommand.execute(model));
    }

    @Test
    public void execute_loginCommand_successfully() throws CommandException {
        Model model = new ModelManager(getAnotherMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        Index index = Index.fromOneBased(5);
        Person toChange = model.getMyFitnessBuddy().getPersonList().get(Index.fromOneBased(5).getZeroBased());
        LoginCommand loginCommand = new LoginCommand(index);
        CommandResult commandResult = loginCommand.execute(model);
        System.out.println(toChange.toString());
        assertEquals(toChange.toString(), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        LoginCommand test1 = new LoginCommand(Index.fromOneBased(2));
        LoginCommand test2 = new LoginCommand(Index.fromOneBased(3));
        assertTrue(test1.equals(test1));
        assertFalse(test1.equals(test2));
    }

}
