package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_DUPLICATE_PREFIX;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CalorieCommand;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;
import seedu.address.testutil.CalorieBuilder;



public class CalorieCommandParserTest {
    private CalorieCommandParser parser = new CalorieCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Output calorie = new CalorieBuilder().withTime("1234")
                                             .withExercise("running")
                                             .withCalorieCount("1234")
                                             .buildOutput();

        Input calorie2 = new CalorieBuilder().withTime("1234")
                                            .withFood("laksa")
                                            .withCalorieCount("1234")
                                            .buildInput();

        String date = LocalDate.now().toString();
        // whitespace only preamble
        assertParseSuccess(parser, "    d/2020-10-10 tp/out t/1234 e/running c/1234",
                new CalorieCommand(calorie, true, "2020-10-10"));

        // no date - current date
        assertParseSuccess(parser, " tp/in t/1234 f/laksa c/1234",
                new CalorieCommand(calorie2, false, date));

        // any date
        assertParseSuccess(parser, " tp/out d/2020-10-10 t/1234 e/running c/1234",
                new CalorieCommand(calorie, true, "2020-10-10"));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieCommand.MESSAGE_USAGE);

        // missing type prefix
        assertParseFailure(parser, "e/running c/1234 t/1230",
                expectedMessage);

        // missing activity prefix
        assertParseFailure(parser, "tp/out c/1234 t/1230",
                expectedMessage);

        // missing activity prefix
        assertParseFailure(parser, "tp/in c/1234 t/1230",
                expectedMessage);

        // missing calorie count prefix
        assertParseFailure(parser, "tp/out e/running t/1230",
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, "tp/out e/running c/1234",
                expectedMessage);

        // missing calorie count prefix
        assertParseFailure(parser, "tp/out t/1230 c/123",
                expectedMessage);

        // double field
        assertParseFailure(parser, "tp/out e/running f/asdf c/123 t/1234",
                expectedMessage);

        // duplicate field
        assertParseFailure(parser, "tp/out e/running f/asdf c/123 t/1234 t/1234",
                MESSAGE_DUPLICATE_PREFIX);

        // duplicate type field
        assertParseFailure(parser, "tp/out tp/in e/running c/123 t/1234",
                MESSAGE_DUPLICATE_PREFIX);
    }

    @Test
    public void parse_invalidValue_failure() {

        // wrong type value
        assertParseFailure(parser, " tp/asdf t/1234 e/running c/123",
                Calorie.MESSAGE_TYPE_CONSTRAINT);

        // wrong time value
        assertParseFailure(parser, " tp/out t/123 e/running c/123",
                Time.MESSAGE_CONSTRAINTS);

        // wrong time value
        assertParseFailure(parser, " tp/out t/2400 e/running c/123",
                Time.MESSAGE_CONSTRAINTS);

        // wrong time value
        assertParseFailure(parser, " tp/out t/23590 e/running c/123",
                Time.MESSAGE_CONSTRAINTS);

        // wrong activity prefix
        assertParseFailure(parser, " tp/out t/1230 f/laksa c/123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieCommand.MESSAGE_USAGE_TYPE_OUT));

        // wrong activity prefix
        assertParseFailure(parser, " tp/in t/1230 e/laksa c/123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieCommand.MESSAGE_USAGE_TYPE_IN));

        // wrong Calorie value
        assertParseFailure(parser, " tp/out t/2359 e/running c/2147483648",
                CalorieCount.MESSAGE_CONSTRAINTS);

        // wrong Calorie value
        assertParseFailure(parser, " tp/out t/2359 e/running c/21474.00",
                CalorieCount.MESSAGE_CONSTRAINTS);

        // wrong Calorie value
        assertParseFailure(parser, " tp/out t/2359 e/running c/0",
                CalorieCount.MESSAGE_CONSTRAINTS);

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieCommand.MESSAGE_USAGE);

        // double activity value
        assertParseFailure(parser, " tp/out t/2359 e/runnnign f/laksa c/100",
                expectedMessage);

        // double activity value
        assertParseFailure(parser, " tp/in t/2359 e/runnnign f/laksa c/100",
                expectedMessage);

    }
}
