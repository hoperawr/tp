package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;


/**
 * Adds a day to the records.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a day to the records. "
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + PREFIX_WEIGHT + "WEIGHT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2020-10-14 "
            + PREFIX_WEIGHT + "70 ";

    public static final String MESSAGE_SUCCESS = "New day added: %1$s";
    public static final String MESSAGE_DUPLICATE_DAY = "This day already exists in the records.";
    public static final String MESSAGE_PAST = "You cannot add a day entry prior "
            + "to the date you have create this profile";
    public static final String MESSAGE_FUTURE = "You cannot add a day entry that is in the future";

    public static final String MESSAGE_NO_LOGIN = "Please login to a profile before adding a new day.";

    private final Day toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Day}
     */
    public AddCommand(Day day) {
        requireNonNull(day);
        toAdd = day;
    }

    /**
     * Checks if the toAdd date is before the starting date
     */
    public boolean isBefore(Date toAdd, Date start) {
        LocalDate add = LocalDate.parse(toAdd.value);
        LocalDate compareTo = LocalDate.parse(start.value);
        return add.isBefore(compareTo);
    }

    /**
     * Checks if the toAdd date is after the current date
     */
    public boolean isAfter(Date toAdd) {
        LocalDate add = LocalDate.parse(toAdd.value);
        LocalDate compareTo = LocalDate.now();
        return add.isAfter(compareTo);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Date start = model.getMyFitnessBuddy().getPerson().getDay();
        Date check = toAdd.getDate();

        if (model.isDefaultProfile()) { //no profile
            throw new CommandException(MESSAGE_NO_LOGIN);
        }

        if (model.hasDay(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DAY);
        }

        if (isBefore(check, start)) {
            throw new CommandException(MESSAGE_PAST);
        }

        if (isAfter(check)) {
            throw new CommandException(MESSAGE_FUTURE);
        }

        model.addDay(toAdd);
        model.updateDay();

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
