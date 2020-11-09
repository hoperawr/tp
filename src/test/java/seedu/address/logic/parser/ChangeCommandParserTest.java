package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NO_CALORIE_DETERMINANT;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NO_DAY_DETERMINANT;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NO_TYPE_DETERMINANT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ChangeCommand;
import seedu.address.logic.commands.ChangeCommand.ChangeCalorieDescriptor;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Time;


public class ChangeCommandParserTest {
    private ChangeCommandParser parser = new ChangeCommandParser();

    private ChangeCalorieDescriptor changeCalorieDescriptor = new ChangeCalorieDescriptor();

    private String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeCommand.MESSAGE_USAGE);

    @Test
    public void parse_missingParts_failure() {
        //no index specified
        assertParseFailure(parser, " tp/out t/1230", MESSAGE_NO_DAY_DETERMINANT);

        //wrong index specified
        assertParseFailure(parser, "1.1 tp/out t/1230", expectedMessage);

        //wrong index specified
        assertParseFailure(parser, "-1 tp/out t/1230", expectedMessage);

        //wrong index specified
        assertParseFailure(parser, "10000000000000000000 tp/out t/1230", expectedMessage);

        //wrong index specified
        assertParseFailure(parser, "asdf tp/out t/1230", expectedMessage);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_NO_DAY_DETERMINANT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NO_TYPE_DETERMINANT);

        // no Calorie Index specified
        assertParseFailure(parser, "1 tp/out", MESSAGE_NO_CALORIE_DETERMINANT);

        // no field change
        assertParseFailure(parser, "1 tp/out i/1", MESSAGE_NOT_EDITED);

        // wrong index specified
        assertParseFailure(parser, "1 tp/out i/1.1 t/1230", expectedMessage);

        // wrong index specified
        assertParseFailure(parser, "1 tp/out i/1000000000000000000000 t/1230", expectedMessage);

        // wrong index specified
        assertParseFailure(parser, "1 tp/out i/-1000 t/1230", expectedMessage);

        // wrong index specified
        assertParseFailure(parser, "1 tp/out i/asd t/1230", expectedMessage);

        // double type specified
        assertParseFailure(parser, "1 tp/out tp/in i/1 t/1230", MESSAGE_DUPLICATE_PREFIX);
    }

    @Test
    public void parse_invalidValue_failure() {
        //wrong type
        assertParseFailure(parser, "1 tp/asd i/1 t/1230", expectedMessage);

        //wrong time
        assertParseFailure(parser, "1 tp/out i/1 t/2400", Time.MESSAGE_CONSTRAINTS);

        //wrong time
        assertParseFailure(parser, "1 tp/out i/1 t/-2400", Time.MESSAGE_CONSTRAINTS);

        //wrong activity
        assertParseFailure(parser, "1 tp/out i/1 f/food", Food.MESSAGE_CONSTRAINTS_WRONG_TYPE);

        //wrong activity
        assertParseFailure(parser, "1 tp/in i/1 e/running", Exercise.MESSAGE_CONSTRAINTS_WRONG_TYPE);

        //empty activity
        assertParseFailure(parser, "1 tp/in i/1 f/", Food.MESSAGE_CONSTRAINTS);

        //empty activity
        assertParseFailure(parser, "1 tp/out i/1 e/", Exercise.MESSAGE_CONSTRAINTS);

        //wrong calorie count
        assertParseFailure(parser, "1 tp/out i/1 c/99999999999999", CalorieCount.MESSAGE_CONSTRAINTS);

        //wrong calorie count
        assertParseFailure(parser, "1 tp/out i/1 c/99.99", CalorieCount.MESSAGE_CONSTRAINTS);

        //wrong calorie count
        assertParseFailure(parser, "1 tp/out i/1 c/-99.99", CalorieCount.MESSAGE_CONSTRAINTS);

        //wrong calorie count
        assertParseFailure(parser, "1 tp/out i/1 c/", CalorieCount.MESSAGE_CONSTRAINTS);

        //wrong date
        assertParseFailure(parser, " d/2020-13-13 tp/out i/1 c/", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = "1 tp/out i/1 e/running t/1200 c/1230";
        Index calorieIndex = Index.fromOneBased(1);

        ChangeCalorieDescriptor descriptor = new ChangeCalorieDescriptor();
        descriptor.setExercise(new Exercise("running"));
        descriptor.setIsOut(true);
        descriptor.setTime(new Time("1200"));
        descriptor.setCalorieCount(new CalorieCount("1230"));

        ChangeCommand expectedCommand = new ChangeCommand(targetIndex, descriptor, calorieIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedWithDate_success() {
        LocalDate date = LocalDate.parse("2020-10-10");
        String userInput = " d/2020-10-10 tp/out i/1 e/running t/1200 c/1230";
        Index calorieIndex = Index.fromOneBased(1);

        ChangeCalorieDescriptor descriptor = new ChangeCalorieDescriptor();
        descriptor.setExercise(new Exercise("running"));
        descriptor.setIsOut(true);
        descriptor.setTime(new Time("1200"));
        descriptor.setCalorieCount(new CalorieCount("1230"));

        ChangeCommand expectedCommand = new ChangeCommand(date, descriptor, calorieIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = "1 tp/out i/1 e/running t/1200";
        Index calorieIndex = Index.fromOneBased(1);

        ChangeCalorieDescriptor descriptor = new ChangeCalorieDescriptor();
        descriptor.setExercise(new Exercise("running"));
        descriptor.setTime(new Time("1200"));
        descriptor.setIsOut(true);

        ChangeCommand expectedCommand = new ChangeCommand(targetIndex, descriptor, calorieIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = "1 tp/out i/1 e/running";
        Index calorieIndex = Index.fromOneBased(1);

        ChangeCalorieDescriptor descriptor = new ChangeCalorieDescriptor();
        descriptor.setExercise(new Exercise("running"));
        descriptor.setIsOut(true);

        ChangeCommand expectedCommand = new ChangeCommand(targetIndex, descriptor, calorieIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
