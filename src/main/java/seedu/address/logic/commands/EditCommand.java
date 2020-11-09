package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;

/**
 * Edits the details of an existing day in My Fitness Buddy.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the day identified "
            + "by the index number used in the displayed day list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_DAY_SUCCESS = "Edited Day: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DAY = "This day already exists in My Fitness Buddy.";

    private final Index index;
    private final EditDayDescriptor editDayDescriptor;

    /**
     * @param index of the day in the filtered day list to edit.
     * @param editDayDescriptor details to edit the day with.
     */
    public EditCommand(Index index, EditDayDescriptor editDayDescriptor) {
        requireNonNull(index);
        requireNonNull(editDayDescriptor);

        this.index = index;
        this.editDayDescriptor = new EditDayDescriptor(editDayDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Day> lastShownList = model.getMyFitnessBuddy().getDayList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
        }

        Day dayToEdit = lastShownList.get(index.getZeroBased());
        Day editedDay = createEditedDay(dayToEdit, editDayDescriptor);

        if (!dayToEdit.isSameDay(editedDay) && model.hasDay(editedDay)) {
            throw new CommandException(MESSAGE_DUPLICATE_DAY);
        }
        editedDay.setAge(dayToEdit.getAge());
        editedDay.setHeight(dayToEdit.getHeight());
        editedDay.setStartingWeight(dayToEdit.getStartingWeight());

        model.setDay(dayToEdit, editedDay);
        model.updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);

        return new CommandResult(String.format(MESSAGE_EDIT_DAY_SUCCESS, editedDay));
    }

    /**
     * Creates and returns a {@code Day} with the details of {@code dayToEdit}
     * edited with {@code editDayDescriptor}.
     */
    public static Day createEditedDay(Day dayToEdit, EditDayDescriptor editDayDescriptor) {
        assert dayToEdit != null;

        Date updatedDate = editDayDescriptor.getDate().orElse(dayToEdit.getDate());
        Weight updatedWeight = editDayDescriptor.getWeight().orElse(dayToEdit.getWeight());
        CalorieManager calorieManager = dayToEdit.getCalorieManager();

        return new Day(updatedDate, updatedWeight, calorieManager);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editDayDescriptor.equals(e.editDayDescriptor);
    }

    /**
     * Stores the details to edit the day with. Each non-empty field value will replace the
     * corresponding field value of the day.
     */
    public static class EditDayDescriptor {
        private Date date;
        private Weight weight;

        public EditDayDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDayDescriptor(EditDayDescriptor toCopy) {
            setDate(toCopy.date);
            setWeight(toCopy.weight);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, weight);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDayDescriptor)) {
                return false;
            }

            // state check
            EditDayDescriptor e = (EditDayDescriptor) other;

            return getDate().equals(e.getDate())
                    && getWeight().equals(e.getWeight());
        }
    }
}
