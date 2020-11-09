package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_DUPLICATE_PREFIX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateCommand;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;


public class UpdateCommandParserTest {
    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        //blank
        assertParseFailure(parser, "", UpdateCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidValue_failure() {
        // wrong name prefix
        assertParseFailure(parser, " n/ ",
                Name.MESSAGE_CONSTRAINTS);

        // wrong name prefix
        assertParseFailure(parser, " n/@asd",
                Name.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " id/0000",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " id/-1000",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " id/5111",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " id/1511",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " id/1100",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " id/1121",
                ID.MESSAGE_CONSTRAINTS);

        // wrong height prefix
        assertParseFailure(parser, " h/123.5",
                Height.MESSAGE_CONSTRAINTS);

        // wrong height prefix
        assertParseFailure(parser, " h/1",
                Height.MESSAGE_CONSTRAINTS);

        // wrong height prefix
        assertParseFailure(parser, " h/300",
                Height.MESSAGE_CONSTRAINTS);

        // wrong weight prefix
        assertParseFailure(parser, " w/@asd23",
                Weight.MESSAGE_CONSTRAINTS);

        // wrong weight prefix
        assertParseFailure(parser, " w/1",
                Weight.MESSAGE_TOO_LIGHT);

        // wrong weight prefix
        assertParseFailure(parser, " w/700",
                Weight.MESSAGE_TOO_HEAVY);

        // duplicate prefix
        assertParseFailure(parser, " w/100 w/100",
                MESSAGE_DUPLICATE_PREFIX);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " n/test id/1111 h/123 w/123";
        UpdateCommand.EditProfileDescriptor editProfileDescriptor = new UpdateCommand.EditProfileDescriptor();
        editProfileDescriptor.setHeight(new Height("123"));
        editProfileDescriptor.setId(new ID("1111"));
        editProfileDescriptor.setName(new Name("test"));
        editProfileDescriptor.setWeight(new Weight("123"));

        UpdateCommand expectedCommand = new UpdateCommand(editProfileDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = " id/1111 h/123";
        UpdateCommand.EditProfileDescriptor editProfileDescriptor = new UpdateCommand.EditProfileDescriptor();
        editProfileDescriptor.setHeight(new Height("123"));
        editProfileDescriptor.setId(new ID("1111"));

        UpdateCommand expectedCommand = new UpdateCommand(editProfileDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        String userInput = " id/1111";
        UpdateCommand.EditProfileDescriptor editProfileDescriptor = new UpdateCommand.EditProfileDescriptor();
        editProfileDescriptor.setId(new ID("1111"));

        UpdateCommand expectedCommand = new UpdateCommand(editProfileDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
