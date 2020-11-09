package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.day.Day;

/**
 * Removes a certain calorie input/output from a particular day
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String NO_AVAILABLE_DAY = "Please add a new day entry for the date intended "
            + "before removing any calorie entry\";";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the calorie identified by the index number and type used in the displayed day list.\n"
            + "Parameters:"
            + PREFIX_CALORIE_TYPE + "IN / OUT"
            + PREFIX_DATE + "Date"
            + PREFIX_INDEX + "Positive index number\n"
            + "Example: " + COMMAND_WORD + PREFIX_CALORIE_TYPE + " tp/out " + PREFIX_INDEX + " 1";

    public static final String MESSAGE_MISSING_DAY_DETERMINANT = "Either input a date or an index to specify which "
            + "date the calorie to be deleted is present but not both";

    public static final String MESSAGE_DELETE_CALORIE_SUCCESS = "Removed Calorie - " + ": %1$s";

    private final Index targetIndex;

    private final Boolean isOut;

    private LocalDate date = null;

    private Index index = null;

    /**
     * @param targetIndex of the calorie in the day to delete
     * @param isOut if the calorie is Output calorie
     * @param date of the day that the calorie belongs to
     */
    public RemoveCommand(Index targetIndex, Boolean isOut, LocalDate date) {
        requireAllNonNull(targetIndex, isOut, date);
        this.targetIndex = targetIndex;
        this.isOut = isOut;
        this.date = date;
    }

    /**
     * @param targetIndex of the calorie in the day to delete
     * @param isOut if the calorie is Output calorie
     * @param index of the day that the calorie belongs to
     */
    public RemoveCommand(Index targetIndex, Boolean isOut, Index index) {
        requireAllNonNull(targetIndex, isOut, index);
        this.targetIndex = targetIndex;
        this.isOut = isOut;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Day> lastShownList = model.getMyFitnessBuddy().getDayList();

        Day editDay;

        if (index != null) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
            } else {
                editDay = lastShownList.get(index.getZeroBased());
            }
        } else {
            if (!model.hasDay(date)) {
                throw new CommandException(NO_AVAILABLE_DAY);
            }
            editDay = model.getDay(date);
        }

        CalorieManager calorieManager = editDay.getCalorieManager();

        Calorie remove = null;
        try {
            remove = calorieManager.getCalorie(isOut, targetIndex);
            editDay.getCalorieManager().removeCalorie(isOut, targetIndex);
        } catch (IllegalValueException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_CALORIE_DISPLAYED_INDEX);
        }

        model.setDay(editDay, editDay);
        model.updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);
        return new CommandResult(String.format(MESSAGE_DELETE_CALORIE_SUCCESS, remove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex) // state check
                && isOut.equals(((RemoveCommand) other).isOut));
    }
}
