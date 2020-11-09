package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.model.calorie.Calorie;


public class RemoveCommandParserTest {
    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        LocalDate date = LocalDate.now();
        // whitespace only preamble
        assertParseSuccess(parser, " 1 tp/out i/1",
                new RemoveCommand(Index.fromOneBased(1), true, Index.fromOneBased(1)));

        assertParseSuccess(parser, " d/2020-11-06 tp/out i/1",
                new RemoveCommand(Index.fromOneBased(1), true, date));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE);

        // missing date and index
        assertParseFailure(parser, " tp/out i/1",
                RemoveCommand.MESSAGE_MISSING_DAY_DETERMINANT);

        // missing type
        assertParseFailure(parser, " 1 i/1",
                expectedMessage);

        // missing index
        assertParseFailure(parser, " 1 tp/out",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE);
        // wrong date value
        assertParseFailure(parser, " d/202020-10-10 tp/out i/1",
                "Date should be in the form of YYYY-MM-DD");

        // wrong index value
        assertParseFailure(parser, " -1 tp/out i/1",
                expectedMessage);

        // wrong index value
        assertParseFailure(parser, " 1.2 tp/out i/1",
                expectedMessage);

        //wrong type value
        assertParseFailure(parser, " 1 tp/test i/1",
                Calorie.MESSAGE_TYPE_CONSTRAINT);

        //wrong index value
        assertParseFailure(parser, " 1 tp/out i/-1",
                expectedMessage);

        //wrong index value
        assertParseFailure(parser, " 1 tp/in i/-1",
                expectedMessage);

    }

}
