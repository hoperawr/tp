package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Profile;

/**
 * Parses input arguments and creates a new CreateCommand object.
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code userInput} of arguments in the context of the CreateCommand
     * and returns a CreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CreateCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_ID, PREFIX_HEIGHT, PREFIX_WEIGHT);

        Profile profile;

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_HEIGHT, PREFIX_WEIGHT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ID id = ParserUtil.parseID(argMultimap.getValue(PREFIX_ID).get());
        Height height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get());
        Weight targetWeight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        profile = new Profile(name, id, height, targetWeight);
        Date start = new Date(LocalDate.now().toString());
        profile.setStartingDay(start);
        /*set the created day to be 1999-06-06 for testing*/
        //profile.setStartingDay(new Date(LocalDate.parse("1999-12-31").toString()));
        return new CreateCommand(profile);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

