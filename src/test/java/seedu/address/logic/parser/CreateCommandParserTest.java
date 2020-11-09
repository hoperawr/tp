package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_DUPLICATE_PREFIX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Profile;

public class CreateCommandParserTest {
    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Profile profile = new Profile(new Name("test"), new ID("1111"), new Height("123"), new Weight("123"));

        // whitespace only preamble
        assertParseSuccess(parser, " n/test id/1111 h/123 w/123",
                new CreateCommand(profile));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, "",
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, "id/1111 h/123 w/123",
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, "n/test h/123 w/123",
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, "n/test id/1111 w/123",
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, "n/test id/1111 h/123",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // wrong name prefix
        assertParseFailure(parser, " n/ id/1111 h/123 w/123",
                Name.MESSAGE_CONSTRAINTS);

        // wrong name prefix
        assertParseFailure(parser, " n/@asd id/1111 h/123 w/123",
                Name.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " n/test id/0000 h/123 w/123",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " n/test id/-1000 h/123 w/123",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " n/test id/5111 h/123 w/123",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " n/test id/1511 h/123 w/123",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " n/test id/1100 h/123 w/123",
                ID.MESSAGE_CONSTRAINTS);

        // wrong id prefix
        assertParseFailure(parser, " n/test id/1121 h/123 w/123",
                ID.MESSAGE_CONSTRAINTS);

        // wrong height prefix
        assertParseFailure(parser, " n/test id/1111 h/123.5 w/123",
                Height.MESSAGE_CONSTRAINTS);

        // wrong height prefix
        assertParseFailure(parser, " n/test id/1111 h/1 w/123",
                Height.MESSAGE_CONSTRAINTS);

        // wrong height prefix
        assertParseFailure(parser, " n/test id/1111 h/300 w/123",
                Height.MESSAGE_CONSTRAINTS);

        // wrong weight prefix
        assertParseFailure(parser, " n/test id/1111 h/123 w/@asd23",
                Weight.MESSAGE_CONSTRAINTS);

        // wrong weight prefix
        assertParseFailure(parser, " n/test id/1111 h/123 w/1",
                Weight.MESSAGE_TOO_LIGHT);

        // wrong weight prefix
        assertParseFailure(parser, " n/test id/1111 h/123 w/700",
                Weight.MESSAGE_TOO_HEAVY);

        // duplicate prefix
        assertParseFailure(parser, " n/test id/1111 h/123 w/100 w/100",
                MESSAGE_DUPLICATE_PREFIX);
    }


}
