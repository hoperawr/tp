package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_2;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_2;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDays.MDAY1;
import static seedu.address.testutil.TypicalDays.MDAY2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
//import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;
import seedu.address.testutil.DayBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Day expectedDay = new DayBuilder(MDAY2).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATE_DESC_2 + WEIGHT_DESC_2, new AddCommand(expectedDay));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Day expectedDay = new DayBuilder(MDAY1).build();
        assertParseSuccess(parser, DATE_DESC_1 + WEIGHT_DESC_1,
                new AddCommand(expectedDay));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing date prefix
        assertParseFailure(parser, VALID_DATE_2 + WEIGHT_DESC_2,
                expectedMessage);

        // missing weight prefix
        assertParseFailure(parser, DATE_DESC_2 + VALID_WEIGHT_2,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DATE_2 + VALID_WEIGHT_2,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        //assertParseFailure(parser, INVALID_DATE_DESC + WEIGHT_DESC_2 + TAG_DESC_HUSBAND
        //        + TAG_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid weight
        assertParseFailure(parser, DATE_DESC_2 + INVALID_WEIGHT_DESC, Weight.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        //assertParseFailure(parser, INVALID_DATE_DESC + WEIGHT_DESC_2 + EMAIL_DESC_2 + INVALID_ADDRESS_DESC,
        //                Date.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DATE_DESC_2 + WEIGHT_DESC_2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
