package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse user input for login command
 * Parse user input to determine which index to change to
 */
public class LoginCommandParser implements Parser<LoginCommand> {


    /**
     * Parse userInput into index and returns a LoginCommand
     * @param userInput
     */
    @Override
    public LoginCommand parse(String userInput) throws ParseException {

        Index index = ParserUtil.parseIndex(userInput);
        return new LoginCommand(index);
    }
}
