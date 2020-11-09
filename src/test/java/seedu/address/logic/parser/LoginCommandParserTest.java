package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LoginCommand;


public class LoginCommandParserTest {
    private LoginCommandParser parser = new LoginCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        // empty index
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX); //
        // invalid index
        assertParseFailure(parser, "-1", MESSAGE_INVALID_INDEX); //
        // invalid index
        assertParseFailure(parser, "10000000000000000000000000", MESSAGE_INVALID_INDEX); //
        // invalid index
        assertParseFailure(parser, "1.1", MESSAGE_INVALID_INDEX); //
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        LoginCommand expectedCommand =
                new LoginCommand(Index.fromOneBased(5));
        assertParseSuccess(parser, "5", expectedCommand);

        assertParseSuccess(parser, "  5", expectedCommand);
    }
}
