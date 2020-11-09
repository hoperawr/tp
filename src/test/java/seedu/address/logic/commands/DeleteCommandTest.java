package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_DELETE_DAY_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DAY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DAY;
import static seedu.address.testutil.TypicalPerson.getSimpleMyFitnessBuddy;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.day.Day;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    @Test
    public void invalid_index() throws CommandException {
        Model model = new ModelManager(getSimpleMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(2));

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX, () ->
                deleteCommand.execute(model));
    }

    @Test
    public void execute_successful() throws CommandException {
        Model model = new ModelManager(getSimpleMyFitnessBuddy(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(1));
        Day toDelete = model.getFilteredDayList().get(0);
        CommandResult commandResult = deleteCommand.execute(model);
        assertEquals(String.format(MESSAGE_DELETE_DAY_SUCCESS, toDelete), commandResult.getFeedbackToUser());;
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_DAY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_DAY);
        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_DAY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));
        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));
        // different day -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
