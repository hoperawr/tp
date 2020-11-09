package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NO_CALORIE_DETERMINANT;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NO_DAY_DETERMINANT;
import static seedu.address.logic.commands.ChangeCommand.MESSAGE_NO_TYPE_DETERMINANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ChangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ChangeCommand object
 */
public class ChangeCommandParser implements Parser<ChangeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CalorieCommand
     * and returns an CalorieCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CALORIE_TYPE, PREFIX_DATE, PREFIX_FOOD,
                        PREFIX_EXERCISE, PREFIX_TIME, PREFIX_CALORIE_COUNT, PREFIX_INDEX);

        if (!(!argMultimap.getPreamble().isEmpty() ^ argMultimap.getValue(PREFIX_DATE).isPresent())) {
            throw new ParseException(MESSAGE_NO_DAY_DETERMINANT);
        }

        Index index = null;
        LocalDate date = null;

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseLocalDate(argMultimap.getValue(PREFIX_DATE).get());
        } else {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeCommand.MESSAGE_USAGE), pe);
            }
        }

        ChangeCommand.ChangeCalorieDescriptor changeCalorieDescriptor = new ChangeCommand.ChangeCalorieDescriptor();

        boolean isOut;
        if (argMultimap.getValue(PREFIX_CALORIE_TYPE).isPresent()) {
            try {
                isOut = ParserUtil.parseType(argMultimap.getValue(PREFIX_CALORIE_TYPE).get());
                changeCalorieDescriptor.setIsOut(isOut);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeCommand.MESSAGE_USAGE), pe);
            }
        } else {
            throw new ParseException(MESSAGE_NO_TYPE_DETERMINANT);
        }

        Index calorieIndex;
        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            try {
                calorieIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ChangeCommand.MESSAGE_USAGE), pe);
            }
        } else {
            throw new ParseException(MESSAGE_NO_CALORIE_DETERMINANT);
        }


        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            changeCalorieDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get()));
        }

        if (argMultimap.getValue(PREFIX_CALORIE_COUNT).isPresent()) {
            changeCalorieDescriptor
                    .setCalorieCount(ParserUtil.parseCalorieCount(argMultimap.getValue(PREFIX_CALORIE_COUNT).get()));
        }

        if (argMultimap.getValue(PREFIX_EXERCISE).isPresent()) {
            changeCalorieDescriptor
                    .setExercise(ParserUtil.parseExercise(argMultimap.getValue(PREFIX_EXERCISE).get(), isOut));
        }

        if (argMultimap.getValue(PREFIX_FOOD).isPresent()) {
            changeCalorieDescriptor.setFood(ParserUtil.parseFood(argMultimap.getValue(PREFIX_FOOD).get(), isOut));
        }

        if (!changeCalorieDescriptor.isAnyFieldEdited()) {
            throw new ParseException(ChangeCommand.MESSAGE_NOT_EDITED);
        }

        if (index != null) {
            return new ChangeCommand(index, changeCalorieDescriptor, calorieIndex);
        } else {
            return new ChangeCommand(date, changeCalorieDescriptor, calorieIndex);
        }
    }
}

