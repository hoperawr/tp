package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.CalorieCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;

/**
 * Parses input arguments and creates a new CalorieCommand object
 */
public class CalorieCommandParser implements Parser<CalorieCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CalorieCommand
     * and returns a CalorieCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CalorieCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CALORIE_TYPE, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_EXERCISE, PREFIX_FOOD, PREFIX_CALORIE_COUNT);

        if (!arePrefixesPresent(argMultimap, PREFIX_CALORIE_TYPE, PREFIX_TIME, PREFIX_CALORIE_COUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieCommand.MESSAGE_USAGE));
        }
        Boolean isOut = ParserUtil.parseType(argMultimap.getValue(PREFIX_CALORIE_TYPE).get());

        String date;
        if (arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            date = argMultimap.getValue(PREFIX_DATE).get();
        } else {
            date = LocalDate.now().toString();
        }

        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        CalorieCount calorieCount = ParserUtil.parseCalorieCount(argMultimap.getValue(PREFIX_CALORIE_COUNT).get());

        Calorie calorie;

        if (arePrefixesPresent(argMultimap, PREFIX_EXERCISE) && arePrefixesPresent(argMultimap, PREFIX_FOOD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_FOOD) && isOut) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CalorieCommand.MESSAGE_USAGE_TYPE_OUT));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_EXERCISE) && !isOut) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CalorieCommand.MESSAGE_USAGE_TYPE_IN));
        }

        if (!isOut) {
            Food food = ParserUtil.parseFood(argMultimap.getValue(PREFIX_FOOD).get(), isOut);
            calorie = new Input(time, food, calorieCount);
        } else {
            Exercise exercise = ParserUtil.parseExercise(argMultimap.getValue(PREFIX_EXERCISE).get(), isOut);
            calorie = new Output(time, exercise, calorieCount);
        }
        return new CalorieCommand(calorie, isOut, date);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
