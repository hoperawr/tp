package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DAY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CalorieCommand;
import seedu.address.logic.commands.ChangeCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditDayDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.StatsCommand;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.day.Day;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.DayBuilder;
import seedu.address.testutil.DayUtil;
import seedu.address.testutil.EditDayDescriptorBuilder;


public class MyFitnessBuddyParserTest {

    private final MyFitnessBuddyParser parser = new MyFitnessBuddyParser();
    //error due to refactoring
    /*
    @Test
    public void parseCommand_add() throws Exception {
        Day day = new DayBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(DayUtil.getAddCommand(day));
        assertEquals(new AddCommand(day), command);
    }
    */

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_DAY.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_DAY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Day day = new DayBuilder().build();
        EditDayDescriptor descriptor = new EditDayDescriptorBuilder(day).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_DAY.getOneBased() + " " + DayUtil.getEditDayDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_DAY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_calorie() throws Exception {
        String userInput = " tp/out d/2020-10-10 e/running c/123 t/1230";
        assertTrue(parser.parseCommand(CalorieCommand.COMMAND_WORD + userInput) instanceof CalorieCommand);
    }

    @Test
    public void parseCommand_remove() throws Exception {
        String userInput = " 1 tp/out i/1";
        assertTrue(parser.parseCommand(RemoveCommand.COMMAND_WORD + userInput) instanceof RemoveCommand);
    }

    @Test
    public void parseCommand_change() throws Exception {
        String userInput = " 1 tp/out i/1 c/123";
        assertTrue(parser.parseCommand(ChangeCommand.COMMAND_WORD + userInput) instanceof ChangeCommand);
    }

    @Test
    public void parseCommand_create() throws Exception {
        String userInput = " n/asd id/1111 w/123 h/123";
        assertTrue(parser.parseCommand(CreateCommand.COMMAND_WORD + userInput) instanceof CreateCommand);
    }

    @Test
    public void parseCommand_update() throws Exception {
        String userInput = " n/asd";
        assertTrue(parser.parseCommand(UpdateCommand.COMMAND_WORD + userInput) instanceof UpdateCommand);
    }

    @Test
    public void parseCommand_login() throws Exception {
        String userInput = " 1";
        assertTrue(parser.parseCommand(LoginCommand.COMMAND_WORD + userInput) instanceof LoginCommand);
    }

    @Test
    public void parseCommand_add() throws Exception {
        String userInput = " d/2020-10-10 w/123";
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + userInput) instanceof AddCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        String userInput = " 1";
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + userInput) instanceof ViewCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_stats() throws Exception {
        StatsCommand command = (StatsCommand) parser.parseCommand(StatsCommand.COMMAND_WORD + " v/all");
        assertEquals(new StatsCommand(true, true, false), command);
    }
}
