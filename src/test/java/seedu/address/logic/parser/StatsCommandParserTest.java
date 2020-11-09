package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StatsCommand;

public class StatsCommandParserTest {

    private StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_viewAllStats_success() {
        String userInput = " v/all";
        StatsCommand expectedCommand = new StatsCommand(true, true, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_viewCalorieStats_success() {
        String userInput = " v/calorie";
        StatsCommand expectedCommand = new StatsCommand(true, false, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_viewWeightStats_success() {
        String userInput = " v/weight";
        StatsCommand expectedCommand = new StatsCommand(false, true, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_viewCommanderStats_success() {
        String userInput = " v/commander";
        StatsCommand expectedCommand = new StatsCommand(false, false, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noParameter_failure() {
        String userInput = "";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingParameter_failure() {
        String userInput = " v/";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_incorrectParameter_failure() {
        String userInput = " v/both";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

}
