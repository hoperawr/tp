package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_BMI;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.BelowCertainBmiPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);

        FindCommand expectedFindCommand2 =
                new FindCommand(new BelowCertainBmiPredicate(23.13));
        assertParseSuccess(parser, " bmi/23.13", expectedFindCommand2);
    }

    @Test
    public void parse_invalidValue_failure() {
        FindCommandParser parser = new FindCommandParser();
        assertParseFailure(parser, " bmi/0",
                MESSAGE_INVALID_BMI);

        assertParseFailure(parser, " bmi/5000",
                MESSAGE_INVALID_BMI);

        assertParseFailure(parser, " bmi/asd",
                MESSAGE_INVALID_BMI);

        assertParseFailure(parser, " bmi/-1090",
                MESSAGE_INVALID_BMI);
    }


}
