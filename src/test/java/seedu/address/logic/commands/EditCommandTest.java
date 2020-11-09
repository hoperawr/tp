package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_2;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_DAY;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_DAY_SUCCESS;
import static seedu.address.logic.commands.EditCommand.createEditedDay;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DAY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DAY;
import static seedu.address.testutil.TypicalPerson.getSimpleMyFitnessBuddy;
import static seedu.address.testutil.TypicalPerson.getSimpleMyFitnessBuddy2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {
    @Test
    public void invalid_index() throws CommandException {
        Model model = new ModelManager(getSimpleMyFitnessBuddy(), new UserPrefs());
        EditCommand.EditDayDescriptor editDayDescriptor = new EditCommand.EditDayDescriptor();
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        EditCommand editCommand = new EditCommand(Index.fromOneBased(2), editDayDescriptor);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX, () ->
                editCommand.execute(model));
    }

    @Test
    public void duplicate_day() throws CommandException {
        Model model = new ModelManager(getSimpleMyFitnessBuddy2(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        EditCommand.EditDayDescriptor editDayDescriptor = new EditCommand.EditDayDescriptor();
        editDayDescriptor.setDate(new Date("2020-10-11"));
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        EditCommand editCommand = new EditCommand(Index.fromOneBased(1), editDayDescriptor);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_DAY, () ->
                editCommand.execute(model));
    }

    @Test
    public void execute_successful() throws CommandException {
        Model model = new ModelManager(getSimpleMyFitnessBuddy2(), new UserPrefs());
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        EditCommand.EditDayDescriptor editDayDescriptor = new EditCommand.EditDayDescriptor();
        editDayDescriptor.setDate(new Date("2020-12-11"));
        model.setCurrentPerson(model.getMyFitnessBuddy().getPersonList().get(0));
        EditCommand editCommand = new EditCommand(Index.fromOneBased(1), editDayDescriptor);
        Day editedDay = createEditedDay(model.getFilteredDayList().get(0), editDayDescriptor);
        CommandResult commandResult = editCommand.execute(model);
        assertEquals(String.format(MESSAGE_EDIT_DAY_SUCCESS, editedDay), commandResult.getFeedbackToUser());;
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_DAY, DESC_1);

        // same values -> returns true
        EditCommand.EditDayDescriptor copyDescriptor = new EditCommand.EditDayDescriptor(DESC_1);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_DAY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_DAY, DESC_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_DAY, DESC_2)));
    }

}
